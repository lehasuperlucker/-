package Server;

public class Company {
    private String name;
    private String form;
    private String district;
    private String region;
    private String employee_number;
    private String owner;
    private String foundation_year;
    private String address;
    private String e_mail;
    private String site;
    private String id;

    public Company(String id, String name, String form, String district, String region, String employee_number, String owner, String foundation_year, String address, String e_mail, String site) {
        this.name=name;
        this.form=form;
        this.district=district;
        this.region=region;
        this.employee_number=employee_number;
        this.owner=owner;
        this.foundation_year=foundation_year;
        this.address=address;
        this.e_mail=e_mail;
        this.site=site;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFoundation_year() {
        return foundation_year;
    }

    public void setFoundation_year(String foundation_year) {
        this.foundation_year = foundation_year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}