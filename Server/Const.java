package Server;

public class Const {
    public static final String USER_TABLE="пользователи";
    public static final String USER_ID="Кодпользователя";
    public static final String USER_USERNAME="Логин";
    public static final String USER_PASSWORD="Пароль";
    public static final String USER_ROLE="Роль";
    public static final String USER_ACCESS="Доступ";

    public static final String COMPANY_TABLE="предприятия";
    public static final String COMPANY_NAME="Название";
    public static final String COMPANY_ID="Кодпредприятия";
    public static final String COMPANY_FORM="Формасообщества";
    public static final String COMPANY_DISTRICT="Район";
    public static final String COMPANY_REGION="Область";
    public static final String COMPANY_EMPLOYEE_NUMBER ="Численностьсотрудников";
    public static final String COMPANY_OWNER="Владелец";
    public static final String COMPANY_FOUNDATION_YEAR="Годоснования";
    public static final String COMPANY_ADDRESS="Адрес";
    public static final String COMPANY_MAIL="mail";
    public static final String COMPANY_SITE="Адресвсетиинтернет";
    public static final String COMPANY_USER_ID="Кодпользователя";

    public static final String TRANSACTION_TABLE="финансоваяоперация";
    public static final String TRANSACTION_ID="Кодоперации";
    public static final String TRANSACTION_NAME="Наименование";
    public static final String TRANSACTION_RISK_LEVEL="Уровеньфинансовогориска";
    public static final String TRANSACTION_DISPERSION="Дисперсия";
    public static final String TRANSACTION_DEVIATION="Срквотклонение";
    public static final String TRANSACTION_VARIATION="Коэффициентвариации";
    public static final String TRANSACTION_COMPANY_ID="Кодпредприятия";
    public static final String TRANSACTION_USER_ID="Кодпользователя";

    public static final String RISK_TABLE="уровеньфинансовогориска";
    public static final String RISK_POSSIBILITY="ВР";
    public static final String RISK_LOSS_SIZE="РП";
    public static final String RISK_USER_ID="Кодпользователя";
    public static final String RISK_COMPANY_ID="Кодпредприятия";
    public static final String RISK_TRANSACTION_ID="Кодоперации";

    public static final String DEVIATION_TABLE="среднееквадратическоеотклонение";
    public static final String DEVIATION_R1="R1";
    public static final String DEVIATION_R2="R2";
    public static final String DEVIATION_R3="R3";
    public static final String DEVIATION_R_AVERAGE="Ravg";
    public static final String DEVIATION_P1="P1";
    public static final String DEVIATION_P2="P2";
    public static final String DEVIATION_P3="P3";
    public static final String DEVIATION_USER_ID="Кодпользователя";
    public static final String DEVIATION_COMPANY_ID="Кодпредприятия";
    public static final String DEVIATION_TRANSACTION_ID="Кодоперации";




}