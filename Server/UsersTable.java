package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersTable extends DataTable implements ResultFromTable {
    public UsersTable(Statement stmt, DatabaseHandler mdbc) {
        super(stmt, mdbc);
    }

    public ResultSet getResultFromTable(String table) {
        ResultSet rs = null;
        try {
            rs = this.stmt.executeQuery("SELECT * FROM " + table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public String checkUserInDB(String username) {
        String var7 = "success";
        ResultSet rs = this.getResultFromTable(Const.USER_TABLE);
        try {
            while (rs.next()) {
                String Username = rs.getString(Const.USER_USERNAME);
                if (Username.equals(username)) {
                    var7 = "fail";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return var7;
    }

    public String checkLoginUser(String username, String password) {
        String var7 = "fail";
        ResultSet rs = this.getResultFromTable(Const.USER_TABLE);
        try {
            while (rs.next()) {
                String tableLogin = rs.getString(Const.USER_USERNAME).trim();
                String tablePassword = rs.getString(Const.USER_PASSWORD).trim();
                String tableRole=rs.getString(Const.USER_ROLE).trim();
                String tableAccess=rs.getString(Const.USER_ACCESS).trim();

                if (tableLogin.equals(username) && tablePassword.equals(password)) {
                        if (tableRole.equals("admin"))
                            var7 = "successAdmin";
                        else if (tableRole.equals("user"))
                            var7 = "successUser";
                        else if (tableRole.equals("root"))
                            var7 = "successRoot";
                        if (tableAccess.equals("закрыт"))
                        var7="blocked";
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.mdbc.close(rs);
        }

        return var7;
    }

    public void AddUser( String username, String password, String role) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_USERNAME + "," + Const.USER_PASSWORD + "," +  Const.USER_ROLE + ")" +
                "VALUES (" + this.quotate(username) + "," + this.quotate(password) + "," + this.quotate(role) + ")";

        try {
            this.stmt.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdUser(String username)
    {
        ResultSet resultSet;
        int id = 0;

        try {
            resultSet = stmt.executeQuery("SELECT " + Const.USER_ID + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + " LIKE '" + username + "';");
            while (resultSet.next())
                id = resultSet.getInt(Const.USER_ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }return id;
    }

    public void deleteUser(String username) {
        int id = getIdUser(username);
        try {
            stmt.executeUpdate("DELETE FROM " + Const.USER_TABLE + " WHERE (" + Const.USER_USERNAME + "='" + username + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void makeAdmin(String username) {
        int id=getIdUser(username);
        try {
            stmt.executeUpdate("update " + Const.USER_TABLE  + " set " + Const.USER_ROLE + "='" + "admin" + "' where (" + Const.USER_USERNAME + "='" + username + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void blockUser(String username)
    {
        try {
            stmt.executeUpdate("update " + Const.USER_TABLE + " set " + Const.USER_ACCESS + "='закрыт' " + " where (" + Const.USER_USERNAME + "='" + username + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unblockUser(String username)
    {
        try {
            stmt.executeUpdate("update " + Const.USER_TABLE + " set " + Const.USER_ACCESS + "='разрешен' " + " where (" + Const.USER_USERNAME + "='" + username + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void checkIfUserTableEmpty() {
        ResultSet resultSet;
        int counter=0,id=1;
        String username, password, role;


        try {
            resultSet=stmt.executeQuery("select " + Const.USER_ID + " from " + Const.USER_TABLE);
            while (resultSet.next()) {
                counter++;
            }
            if (counter==0) {
                username="june";
                password="21062000";
                role="root";
                stmt.executeUpdate("insert into " + Const.USER_TABLE + "(" + Const.USER_ID + "," + Const.USER_USERNAME + "," + Const.USER_PASSWORD + "," + Const.USER_ROLE + ")" + " values " + "(" + id + "," + this.quotate(username) + "," + this.quotate(password) + "," + this.quotate(role) + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void sendUserdata() {
//        try {
//            ResultSet resultSet=stmt.executeQuery("select * from " + Const.USER_TABLE);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

//    public int getLastUserCode() {
//        ResultSet resultSet;
//        int counter=0;
//
//        try {
//            resultSet=stmt.executeQuery("select " + Const.USER_ID + " from " + Const.USER_TABLE);
//            while (resultSet.next()) {
//                counter=resultSet.getInt(Const.USER_ID);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return counter;
//    }
}