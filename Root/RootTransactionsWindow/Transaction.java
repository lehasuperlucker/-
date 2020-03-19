package sample.Root.RootTransactionsWindow;

public class Transaction {
    private String transactionIdColumn;
    private String companyIdColumn;
    private String userIdColumn;
    private String nameColumn;
    private String riskColumn;
    private String deviationColumn;
    private String dispersionColumn;
    private String variationColumn;

    public String getTransactionIdColumn() {
        return transactionIdColumn;
    }

    public void setTransactionIdColumn(String transactionIdColumn) {
        this.transactionIdColumn = transactionIdColumn;
    }

    public String getCompanyIdColumn() {
        return companyIdColumn;
    }

    public void setCompanyIdColumn(String companyIdColumn) {
        this.companyIdColumn = companyIdColumn;
    }

    public String getUserIdColumn() {
        return userIdColumn;
    }

    public void setUserIdColumn(String userIdColumn) {
        this.userIdColumn = userIdColumn;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    public String getRiskColumn() {
        return riskColumn;
    }

    public void setRiskColumn(String riskColumn) {
        this.riskColumn = riskColumn;
    }

    public String getDeviationColumn() {
        return deviationColumn;
    }

    public void setDeviationColumn(String deviationColumn) {
        this.deviationColumn = deviationColumn;
    }

    public String getDispersionColumn() {
        return dispersionColumn;
    }

    public void setDispersionColumn(String dispersionColumn) {
        this.dispersionColumn = dispersionColumn;
    }

    public String getVariationColumn() {
        return variationColumn;
    }

    public void setVariationColumn(String variationColumn) {
        this.variationColumn = variationColumn;
    }

    public Transaction(String transactionIdColumn, String companyIdColumn, String userIdColumn, String nameColumn, String riskColumn, String deviationColumn, String dispersionColumn, String variationColumn) {
        this.transactionIdColumn = transactionIdColumn;
        this.companyIdColumn = companyIdColumn;
        this.userIdColumn = userIdColumn;
        this.nameColumn = nameColumn;
        this.riskColumn = riskColumn;
        this.deviationColumn = deviationColumn;
        this.dispersionColumn = dispersionColumn;
        this.variationColumn = variationColumn;
    }
}
