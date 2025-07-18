package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO department "
                    + "( Name) "
                    + "VALUES "
                    + "( ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rows = st.executeUpdate();
            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);

            } else {
                throw new DbException("Nenhuma linha afetada");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatment(st);
            DB.closeConnection();
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE department dp "
                    + "SET Name = ? "
                    + "WHERE dp.Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatment(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM department dp "
                    + "WHERE dp.Id = (?)");

            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatment(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department dp "
                    + "WHERE dp.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                return dep;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            //instanciado dentro para cada next criar um novo objeto
            while (rs.next()) {
                Department dep = new Department();

                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));

                list.add(dep);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }

}
