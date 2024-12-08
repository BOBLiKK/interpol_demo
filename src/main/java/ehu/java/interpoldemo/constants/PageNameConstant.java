package ehu.java.interpoldemo.constants;

public final class PageNameConstant {

    private PageNameConstant() {
    }
    //public pages
    public static final String INDEX_PAGE = "index.jsp";
    public static final String LOGIN_PAGE = "pages/login.jsp";
    public static final String REGISTER_USER = "pages/registeruser.jsp";

    //GUEST pages
    public static final String MAIN_PAGE = "pages/guest/main.jsp";

    //ADMIN pages
    public static final String ADMIN_DASHBOARD = "pages/admin/dashboard.jsp";
    public static final String ADD_CRIMINAL = "pages/admin/addCriminal.jsp";
    public static final String ADMIN_CRIMINAL_LIST = "pages/admin/criminalList.jsp";
    public static final String EDIT_CRIMINAL = "pages/admin/editcriminal.jsp";

    //Error pages
    public static final String ERROR_404_PAGE = "pages/error/404.jsp";
    public static final String ERROR_500_PAGE = "pages/error/500.jsp";

    //Properties
    public static final String MAIL_PROPERTIES = "mail.properties";
}
