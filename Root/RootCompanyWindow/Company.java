package sample.Root.RootCompanyWindow;

public class Company {
    String userIdColumn;
    String companyIdColumn;
    String nameColumn;
    String formColumn;
    String districtColumn;
    String regionColumn;
    String employeeColumn;
    String ownerColumn;
    String yearColumn;
    String addressColumn;
    String mailColumn;
    String siteColumn;

    public Company(String userIdColumn, String companyIdColumn, String nameColumn, String formColumn, String districtColumn, String regionColumn, String employeeColumn, String ownerColumn, String yearColumn, String addressColumn, String mailColumn, String siteColumn) {
        this.userIdColumn = userIdColumn;
        this.companyIdColumn = companyIdColumn;
        this.nameColumn = nameColumn;
        this.formColumn = formColumn;
        this.districtColumn = districtColumn;
        this.regionColumn = regionColumn;
        this.employeeColumn = employeeColumn;
        this.ownerColumn = ownerColumn;
        this.yearColumn = yearColumn;
        this.addressColumn = addressColumn;
        this.mailColumn = mailColumn;
        this.siteColumn = siteColumn;
    }



    public String getUserIdColumn() {
        return userIdColumn;
    }

    public void setUserIdColumn(String userIdColumn) {
        this.userIdColumn = userIdColumn;
    }

    public String getCompanyIdColumn() {
        return companyIdColumn;
    }

    public void setCompanyIdColumn(String companyIdColumn) {
        this.companyIdColumn = companyIdColumn;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    public String getFormColumn() {
        return formColumn;
    }

    public void setFormColumn(String formColumn) {
        this.formColumn = formColumn;
    }

    public String getDistrictColumn() {
        return districtColumn;
    }

    public void setDistrictColumn(String districtColumn) {
        this.districtColumn = districtColumn;
    }

    public String getRegionColumn() {
        return regionColumn;
    }

    public void setRegionColumn(String regionColumn) {
        this.regionColumn = regionColumn;
    }

    public String getEmployeeColumn() {
        return employeeColumn;
    }

    public void setEmployeeColumn(String employeeColumn) {
        this.employeeColumn = employeeColumn;
    }

    public String getOwnerColumn() {
        return ownerColumn;
    }

    public void setOwnerColumn(String ownerColumn) {
        this.ownerColumn = ownerColumn;
    }

    public String getYearColumn() {
        return yearColumn;
    }

    public void setYearColumn(String yearColumn) {
        this.yearColumn = yearColumn;
    }

    public String getAddressColumn() {
        return addressColumn;
    }

    public void setAddressColumn(String addressColumn) {
        this.addressColumn = addressColumn;
    }

    public String getMailColumn() {
        return mailColumn;
    }

    public void setMailColumn(String mailColumn) {
        this.mailColumn = mailColumn;
    }

    public String getSiteColumn() {
        return siteColumn;
    }

    public void setSiteColumn(String siteColumn) {
        this.siteColumn = siteColumn;
    }
}
