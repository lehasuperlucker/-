package Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionsTable extends DataTable implements ResultFromTable {
    public TransactionsTable(Statement stmt, DatabaseHandler mdbc) {
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

    public String calculateRisk(String transname)
    {
        double risk, possibility = 0, loss_size = 0;
        String risk_str = null;
        int idt=this.getTransactionId(transname);
        try {
            ResultSet resultSet = stmt.executeQuery("select " + Const.RISK_POSSIBILITY + " from " + Const.RISK_TABLE + " where (" + Const.RISK_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next()) {
                possibility=resultSet.getDouble(Const.RISK_POSSIBILITY);
            }
            resultSet=stmt.executeQuery("select " + Const.RISK_LOSS_SIZE + " from " + Const.RISK_TABLE + " where (" + Const.RISK_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next()) {
                loss_size = resultSet.getDouble(Const.RISK_LOSS_SIZE);
            }
            risk=possibility*loss_size;
            risk_str=String.valueOf(risk);
            stmt.executeUpdate(" UPDATE " + Const.TRANSACTION_TABLE + " SET " + Const.TRANSACTION_RISK_LEVEL + "='" + risk_str + "'" +  " WHERE (" + Const.TRANSACTION_ID + "='" + idt + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return risk_str;
    }

    public String calculateDeviation(String transname) {
        double r1 = 0,r2 = 0,r3 = 0,ravg = 0,p1 = 0,p2 = 0,p3 = 0,deviation;
        String deviation_str = null;
        int idt=this.getTransactionId(transname);
        try {
            ResultSet resultSet=stmt.executeQuery("select " + Const.DEVIATION_R1 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r1=resultSet.getDouble(Const.DEVIATION_R1);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_R2 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r2=resultSet.getDouble(Const.DEVIATION_R2);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_R3 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r3=resultSet.getDouble(Const.DEVIATION_R3);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_R_AVERAGE + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                ravg=resultSet.getDouble(Const.DEVIATION_R_AVERAGE);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_P1 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p1=resultSet.getDouble(Const.DEVIATION_P1);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_P2 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p2=resultSet.getDouble(Const.DEVIATION_P2);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_P3 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p3=resultSet.getDouble(Const.DEVIATION_P3);
            deviation=Math.sqrt(((r1-ravg)*(r1-ravg)*p1)+((r2-ravg)*(r2-ravg)*p2)+((r3-ravg)*(r3-ravg)*p3));
            deviation_str=String.valueOf(deviation);
            stmt.executeUpdate(" UPDATE " + Const.TRANSACTION_TABLE + " SET " + Const.TRANSACTION_DEVIATION + "='" + deviation_str + "' WHERE (" + Const.TRANSACTION_ID + "='" + idt + "');"); } catch (SQLException e) {
            e.printStackTrace();
        }

        return deviation_str;
    }

    public String calculateDispersion(String transname) {
        double r1 = 0,r2 = 0,r3 = 0,ravg = 0,p1 = 0,p2 = 0,p3 = 0,dispersion;
        String dispersion_str = null;
        int idt=this.getTransactionId(transname);
        try {
            ResultSet resultSet = stmt.executeQuery("select " + Const.DEVIATION_R1 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r1 = resultSet.getDouble(Const.DEVIATION_R1);
            resultSet = stmt.executeQuery("select " + Const.DEVIATION_R2 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r2 = resultSet.getDouble(Const.DEVIATION_R2);
            resultSet = stmt.executeQuery("select " + Const.DEVIATION_R3 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r3 = resultSet.getDouble(Const.DEVIATION_R3);
            resultSet = stmt.executeQuery("select " + Const.DEVIATION_R_AVERAGE + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                ravg = resultSet.getDouble(Const.DEVIATION_R_AVERAGE);
            resultSet = stmt.executeQuery("select " + Const.DEVIATION_P1 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p1 = resultSet.getDouble(Const.DEVIATION_P1);
            resultSet = stmt.executeQuery("select " + Const.DEVIATION_P2 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p2 = resultSet.getDouble(Const.DEVIATION_P2);
            resultSet = stmt.executeQuery("select " + Const.DEVIATION_P3 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p3 = resultSet.getDouble(Const.DEVIATION_P3);
            dispersion = ((r1 - ravg) * (r1 - ravg) * p1) + ((r2 - ravg) * (r2 - ravg) * p2) + ((r3 - ravg) * (r3 - ravg) * p3);
            dispersion_str = String.valueOf(dispersion);
            stmt.executeUpdate(" UPDATE " + Const.TRANSACTION_TABLE + " SET " + Const.TRANSACTION_DISPERSION + "='" + dispersion_str + "' WHERE (" + Const.TRANSACTION_ID + "='" + idt + "');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dispersion_str;
    }

    public String calculateVariation(String transname) {
        double r1 = 0,r2 = 0,r3 = 0,ravg = 0,p1 = 0,p2 = 0,p3 = 0,deviation,coef;
        String coef_str = null;
        int idt=this.getTransactionId(transname);
        try {
            ResultSet resultSet=stmt.executeQuery("select " + Const.DEVIATION_R1 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r1=resultSet.getDouble(Const.DEVIATION_R1);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_R2 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r2=resultSet.getDouble(Const.DEVIATION_R2);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_R3 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                r3=resultSet.getDouble(Const.DEVIATION_R3);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_R_AVERAGE + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                ravg=resultSet.getDouble(Const.DEVIATION_R_AVERAGE);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_P1 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p1=resultSet.getDouble(Const.DEVIATION_P1);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_P2 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p2=resultSet.getDouble(Const.DEVIATION_P2);
            resultSet=stmt.executeQuery("select " + Const.DEVIATION_P3 + " from " + Const.DEVIATION_TABLE + " where (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            while (resultSet.next())
                p3=resultSet.getDouble(Const.DEVIATION_P3);
            deviation=Math.sqrt(((r1-ravg)*(r1-ravg)*p1)+((r2-ravg)*(r2-ravg)*p2)+((r3-ravg)*(r3-ravg)*p3));
            coef=deviation/ravg;
            coef_str=String.valueOf(coef);
            stmt.executeUpdate(" UPDATE " + Const.TRANSACTION_TABLE + " SET " + Const.TRANSACTION_VARIATION + "='" + coef_str + "' WHERE (" + Const.TRANSACTION_ID + "='" + idt + "');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coef_str;
    }

    public void deleteTransaction(String name)
    {
        int idt=this.getTransactionId(name);
        try {
            stmt.executeUpdate("DELETE FROM " + Const.DEVIATION_TABLE + " WHERE (" + Const.DEVIATION_TRANSACTION_ID + "='" + idt + "');");
            stmt.executeUpdate("DELETE FROM " + Const.RISK_TABLE + " WHERE (" + Const.RISK_TRANSACTION_ID + "='" + idt + "');");
            stmt.executeUpdate("DELETE FROM " + Const.TRANSACTION_TABLE + " WHERE (" + Const.TRANSACTION_ID + "='" + idt + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRisk(String transname,int compId,int userId,String vr,String rp) {
        int transId=getTransactionId(transname);
        double vR,rP;
        vR=Double.parseDouble(vr);
        rP=Double.parseDouble(rp);
        try {
            this.stmt.executeUpdate("insert into " + Const.RISK_TABLE  + "("  + Const.RISK_TRANSACTION_ID + ","
                    + Const.RISK_COMPANY_ID + "," + Const.RISK_USER_ID + "," + Const.RISK_POSSIBILITY + ","
                    + Const.RISK_LOSS_SIZE + ") values (" + transId + "," + compId + "," + userId + "," + vR + "," + rP + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDeviation(String transname,int compId,int userId,String r1,String r2,String r3,String ravg,String p1,String p2,String p3) {
        int transId=getTransactionId(transname);
        double rd1,rd2,rd3,rdavg,pd1,pd2,pd3;
        rd1=Double.parseDouble(r1);
        rd2=Double.parseDouble(r2);
        rd3=Double.parseDouble(r3);
        rdavg=Double.parseDouble(ravg);
        pd1=Double.parseDouble(p1);
        pd2=Double.parseDouble(p2);
        pd3=Double.parseDouble(p3);
        try {
            this.stmt.executeUpdate("insert into " + Const.DEVIATION_TABLE + "(" + Const.DEVIATION_TRANSACTION_ID + ","
            + Const.DEVIATION_COMPANY_ID + "," + Const.DEVIATION_USER_ID + "," + Const.DEVIATION_R1 + ","
            + Const.DEVIATION_R2  + "," + Const.DEVIATION_R3 + "," + Const.DEVIATION_R_AVERAGE + ","
                    + Const.DEVIATION_P1 + "," + Const.DEVIATION_P2 + "," + Const.DEVIATION_P3 + ") values (" + transId + "," + compId + "," +
                    userId + "," + rd1 + "," + rd2 + "," + rd3 + "," + rdavg + "," + pd1 + "," + pd2 + "," + pd3 + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(String transname,int compId,int userId) {
        try {
            this.stmt.executeUpdate("insert into " + Const.TRANSACTION_TABLE + "(" + Const.TRANSACTION_COMPANY_ID + ","
                    + Const.TRANSACTION_USER_ID + "," + Const.TRANSACTION_NAME  + ")" + " values (" + compId + "," + userId + "," + this.quotate(transname) + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getTransactionId(String name){
        ResultSet resultSet;
        int id = 0;

        try {
            resultSet = stmt.executeQuery("SELECT " + Const.TRANSACTION_ID + " FROM " + Const.TRANSACTION_TABLE + " WHERE " + Const.TRANSACTION_NAME + "='" + name + "';");
            while (resultSet.next())
                id = resultSet.getInt(Const.TRANSACTION_ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }return id;
    }

    public void save (Boolean bool, String text)
    {
        try(FileOutputStream fos=new FileOutputStream("D://Учебные материалы//Client//transaction.txt", bool))
        {
            byte[] buffer = text.getBytes();
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void saveTransactionToFile(String transname) {
        int idt=getTransactionId(transname);
        String companyName = null,risk = null,deviation = null,dispersion = null,variation = null,name = null;
        int idc = 0;
        try {
            ResultSet resultSet = stmt.executeQuery("select * from " + Const.TRANSACTION_TABLE + " where " + Const.TRANSACTION_ID + "='" + idt + "';");
            while (resultSet.next()) {
                idc = resultSet.getInt(Const.TRANSACTION_COMPANY_ID);
                name=resultSet.getString(Const.TRANSACTION_NAME);
                risk=resultSet.getString(Const.TRANSACTION_RISK_LEVEL);
                deviation=resultSet.getString(Const.TRANSACTION_DEVIATION);
                dispersion=resultSet.getString(Const.TRANSACTION_DISPERSION);
                variation=resultSet.getString(Const.TRANSACTION_VARIATION);
            }
            resultSet=stmt.executeQuery("select " + Const.COMPANY_NAME + " from " + Const.COMPANY_TABLE + " where " + Const.COMPANY_ID + "='" + idc + "';");
            while (resultSet.next())
                companyName=resultSet.getString(Const.COMPANY_NAME);
            resultSet=stmt.executeQuery("select " + Const.TRANSACTION_COMPANY_ID + " from " + Const.TRANSACTION_TABLE + " where " + Const.TRANSACTION_ID + "='" + idt + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String text="Название предприятия: " + companyName + "\r\n";
        save(false,text);
        String text1="Название финансовой операции: " + name + "\r\n";
        save(true,text1);
        String text2="Уровень финансового риска: " + risk + "\r\n";
        save(true,text2);
        String text3="Среднее квадратическое отклонение: " + deviation + "\r\n";
        save(true,text3);
        String text4="Дисперсия: " + dispersion + "\r\n";
        save(true,text4);
        String text5="Коэффициент вариации: " + variation + "\r\n";
        save(true,text5);
    }

    public String sendDataForPie(String transname) {
        String data="";
        String risk = null;
        String dev = null;
        String dis = null;
        int idt=getTransactionId(transname);
        try {
            ResultSet resultSet = stmt.executeQuery("select " + Const.TRANSACTION_RISK_LEVEL + " from " + Const.TRANSACTION_TABLE + " where " + Const.TRANSACTION_ID + "='" + idt + "';");
            while (resultSet.next())
                risk=resultSet.getString(Const.TRANSACTION_RISK_LEVEL);
            resultSet=stmt.executeQuery("select " + Const.TRANSACTION_DEVIATION + " from " + Const.TRANSACTION_TABLE + " where " + Const.TRANSACTION_ID + "='" + idt + "';");
            while (resultSet.next())
                dev=resultSet.getString(Const.TRANSACTION_DEVIATION);
            resultSet=stmt.executeQuery("select " + Const.TRANSACTION_DISPERSION + " from " + Const.TRANSACTION_TABLE + " where " + Const.TRANSACTION_ID + "='" + idt + "';");
            while (resultSet.next())
                dis=resultSet.getString(Const.TRANSACTION_DISPERSION);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data=risk + "," + dev + "," + dis;
        return data;
    }
}