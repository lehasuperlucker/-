package Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompaniesTable extends DataTable implements ResultFromTable {
    public CompaniesTable(Statement stmt, DatabaseHandler mdbc) {
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

    public void addCompany(String name, String form, String district, String region, String employee_number, String owner, String foundation_year, String address, String e_mail, String site, int userId) {
        String insert="INSERT INTO " + Const.COMPANY_TABLE + "(" + Const.COMPANY_NAME + "," +
                Const.COMPANY_FORM + "," + Const.COMPANY_DISTRICT + "," + Const.COMPANY_REGION + "," +
                Const.COMPANY_EMPLOYEE_NUMBER + "," + Const.COMPANY_OWNER + "," + Const.COMPANY_FOUNDATION_YEAR + "," +
                Const.COMPANY_ADDRESS + "," + Const.COMPANY_MAIL + "," + Const.COMPANY_SITE + "," + Const.COMPANY_USER_ID + ")" +
                " VALUES (" + this.quotate(name) + "," + this.quotate(form) + "," + this.quotate(district) + "," + this.quotate(region) + "," + this.quotate(employee_number) + "," + this.quotate(owner) + "," + this.quotate(foundation_year) + "," + this.quotate(address) + "," + this.quotate(e_mail) + "," + this.quotate(site) + "," + userId + ")";

        try {
            this.stmt.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delCompany(String name)
    {
        int id=this.getCompanyId(name);
        try {
            stmt.executeUpdate("DELETE FROM " + Const.DEVIATION_TABLE + " WHERE (" + Const.DEVIATION_COMPANY_ID + " LIKE '" + id + "');");
            stmt.executeUpdate("DELETE FROM " + Const.RISK_TABLE + " WHERE (" + Const.RISK_COMPANY_ID + " LIKE '" + id + "');");
            stmt.executeUpdate("DELETE FROM " + Const.TRANSACTION_TABLE + " WHERE (" + Const.TRANSACTION_COMPANY_ID + " LIKE '" + id + "');");
            stmt.executeUpdate("DELETE FROM " + Const.COMPANY_TABLE + " WHERE (" + Const.COMPANY_NAME + "='" + name + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getCompanyId(String name) {
        ResultSet resultSet;
        int id = 0;

        try {
            resultSet = stmt.executeQuery("SELECT " + Const.COMPANY_ID + " FROM " + Const.COMPANY_TABLE + " WHERE " + Const.COMPANY_NAME + "='" + name + "';");
            while (resultSet.next())
                id = resultSet.getInt(Const.COMPANY_ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }return id;
    }
    
    public String getCompanyName(int id) {
        ResultSet resultSet;
        String companyName = null;
        try {
            resultSet = stmt.executeQuery("SELECT " + Const.COMPANY_NAME + " FROM " + Const.COMPANY_TABLE + " WHERE " + Const.COMPANY_ID + "='" + id + "';");
            while (resultSet.next())
                companyName=resultSet.getString(Const.COMPANY_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyName;
    }



}

