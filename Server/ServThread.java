package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServThread extends Thread {
    private InetAddress addr;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private String clientMessage;
    private int counter;
    private DatabaseHandler mdbc;
    private Statement stmt;
    private UsersTable usersTable;
    private CompaniesTable companiesTable;
    private TransactionsTable transactionsTable;

    public ServThread(Socket s, int counter) throws IOException, SQLException, ClassNotFoundException {
        this.counter=counter;
        this.os = new ObjectOutputStream(s.getOutputStream());
        this.is = new ObjectInputStream(s.getInputStream());
        this.addr = s.getInetAddress();
        this.mdbc = new DatabaseHandler();
        Connection conn = this.mdbc.getDbConnection();

        try {
            this.stmt = conn.createStatement();
            this.usersTable = new UsersTable(this.stmt, this.mdbc);
            this.companiesTable = new CompaniesTable(this.stmt, this.mdbc);
            this.transactionsTable = new TransactionsTable(this.stmt, this.mdbc);
        } catch (SQLException e5) {
            System.out.println(e5);
        }
    }

    public void writeObj(String str) {
        this.clientMessage = str;

        try {
            this.os.writeObject(this.clientMessage);
        } catch (IOException e3) {
            System.err.println("I/О thread error" + e3);
        }
    }

    public void run() {
        boolean i=false;
        String messageToClient="";
        String str;
        String ThreadStop="";

        try {
            System.out.println("Сервер ожидает от клиента действий");

            while (!ThreadStop.equals("Exit")) {
                str=(String) this.is.readObject();
                String[] messageParts=str.split(",");
                this.usersTable.checkIfUserTableEmpty();
                switch (messageParts[0]) {
                    case "checkLoginUser":
                        {
                        String userLogin = messageParts[1];
                        String userPassword = messageParts[2];
                        messageToClient = this.usersTable.checkLoginUser(userLogin, userPassword);
                        this.writeObj(messageToClient);
                        break;
                        }
                    case "checkUserInDB":
                    {
                        messageToClient=usersTable.checkUserInDB(messageParts[1]);
                        this.writeObj(messageToClient);
                        break;
                    }
                    case "addUser":
                    {
                        String userLogin=messageParts[1];
                        String userPassword=messageParts[2];
                        String role="user";
                        this.usersTable.AddUser(userLogin,userPassword,role);
                        break;
                    }
                    case "addCompany":
                    {
                        String name=messageParts[1];
                        String form=messageParts[2];
                        String district=messageParts[3];
                        String region=messageParts[4];
                        String employee_number=messageParts[5];
                        String owner=messageParts[6];
                        String foundation_year=messageParts[7];
                        String address=messageParts[8];
                        String e_mail=messageParts[9];
                        String website=messageParts[10];
                        String userName=messageParts[11];
                        int userId=this.usersTable.getIdUser(userName);
                        this.companiesTable.addCompany(name,form,district,region,employee_number,owner,foundation_year,address,e_mail,website,userId);
                        messageToClient="success";
                        this.writeObj(messageToClient);
                        break;
                    }
                    case "sendUserInfo":
                    {
                        ResultSet resultSet = null;
                        try {
                            resultSet=stmt.executeQuery("select * from " + Const.USER_TABLE);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                            while (resultSet.next())
                            {
                                this.os.writeObject(resultSet.getString(1));
                                this.os.writeObject(resultSet.getString(2));
                                this.os.writeObject(resultSet.getString(4));
                                this.os.writeObject(resultSet.getString(5));
                            }
                            this.os.writeObject("end");
                            break;
                    }
                    case "sendCompanyInfo":
                    {
                        ResultSet resultSet = null;
                        try {
                            resultSet=stmt.executeQuery("select * from " + Const.COMPANY_TABLE);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        while (resultSet.next())
                        {
                            this.os.writeObject(resultSet.getString(1));
                            this.os.writeObject(resultSet.getString(12));
                            this.os.writeObject(resultSet.getString(2));
                            this.os.writeObject(resultSet.getString(3));
                            this.os.writeObject(resultSet.getString(4));
                            this.os.writeObject(resultSet.getString(5));
                            this.os.writeObject(resultSet.getString(6));
                            this.os.writeObject(resultSet.getString(7));
                            this.os.writeObject(resultSet.getString(8));
                            this.os.writeObject(resultSet.getString(9));
                            this.os.writeObject(resultSet.getString(10));
                            this.os.writeObject(resultSet.getString(11));

                        }
                        this.os.writeObject("end");
                        break;
                    }
                    case "sendTransactionsInfo":
                    {
                        int idc=this.companiesTable.getCompanyId(messageParts[1]);
                        ResultSet resultSet=null;
                        try {
                            resultSet=stmt.executeQuery("select * from " + Const.TRANSACTION_TABLE + " where (" + Const.TRANSACTION_COMPANY_ID + "='" + idc + "');");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        while (resultSet.next())
                        {
                            this.os.writeObject(resultSet.getString(Const.TRANSACTION_ID));
                            this.os.writeObject(resultSet.getString(Const.TRANSACTION_COMPANY_ID));
                            this.os.writeObject(resultSet.getString(Const.TRANSACTION_USER_ID));
                            this.os.writeObject(resultSet.getString(Const.TRANSACTION_NAME));
                            this.os.writeObject(resultSet.getString(Const.TRANSACTION_RISK_LEVEL));
                            this.os.writeObject(resultSet.getString(Const.TRANSACTION_DEVIATION));
                            this.os.writeObject(resultSet.getString(Const.TRANSACTION_DISPERSION));
                            this.os.writeObject(resultSet.getString(Const.TRANSACTION_VARIATION));
                        }

                        this.os.writeObject("end");

                        break;
                    }
                    case "deleteUser":
                    {
                        this.usersTable.deleteUser(messageParts[1]);
                        break;
                    }
                    case "makeAdmin":
                    {
                        this.usersTable.makeAdmin(messageParts[1]);
                        break;
                    }
                    case "blockUser":
                    {
                        this.usersTable.blockUser(messageParts[1]);
                        break;
                    }
                    case "unblockUser":
                    {
                        this.usersTable.unblockUser(messageParts[1]);
                        break;
                    }
                    case "deleteCompany":
                    {
                        this.companiesTable.delCompany(messageParts[1]);
                        break;
                    }
                    case "deleteTransaction":
                    {
                        this.transactionsTable.deleteTransaction(messageParts[1]);
                        break;
                    }
                    case "addTransaction":
                    {
                        int compId=this.companiesTable.getCompanyId(messageParts[11]);
                        int userId=this.usersTable.getIdUser(messageParts[12]);
                        this.transactionsTable.addTransaction(messageParts[1],compId,userId);
                        this.transactionsTable.addRisk(messageParts[1],compId,userId,messageParts[2],messageParts[3]);
                        this.transactionsTable.addDeviation(messageParts[1],compId,userId,messageParts[4],messageParts[5],messageParts[6],messageParts[10],messageParts[7],messageParts[8],messageParts[9]);
                        break;
                    }
                    case "calculateRisk":
                    {
                        this.writeObj(this.transactionsTable.calculateRisk(messageParts[1]));
                        break;
                    }
                    case "calculateDeviation":
                    {
                        this.writeObj(this.transactionsTable.calculateDeviation(messageParts[1]));
                        break;
                    }
                    case "calculateDispersion":
                    {
                        this.writeObj(this.transactionsTable.calculateDispersion(messageParts[1]));
                        break;
                    }
                    case "calculateVariation":
                    {
                        this.writeObj(this.transactionsTable.calculateVariation(messageParts[1]));
                        break;
                    }
                    case "saveTransactionToFile":
                    {
                        this.transactionsTable.saveTransactionToFile(messageParts[1]);
                        break;
                    }
                    case "sendPieChartData":
                    {
                        this.writeObj(this.transactionsTable.sendDataForPie(messageParts[1]));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private void disconnect() {
        try {
            if (this.os != null) {
                this.os.close();
            }
            if (this.is != null) {
                this.is.close();
            }
            System.out.println(this.addr.getHostName() + " Закрытие соединения " + this.counter + "го клиента");
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            this.interrupt();
        }
    }
}