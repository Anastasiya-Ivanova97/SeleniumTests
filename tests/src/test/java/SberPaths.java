public class SberPaths {
    private String findGeoposition = "//*[@class=\"hd-ft-region\"]/descendant::span";
    private String point = "//a[text()='Нижегородская область']";
    private String newPath = "//*[@class=\"hd-ft-region\"]/descendant::span";
    private String footerPath = "//footer";
    private String soc = "//ul[@class='footer__social']";

    public String getFindGeoposition() {
        return findGeoposition;
    }

    public String getPoint() {
        return point;
    }

    public String getNewPath() {
        return newPath;
    }

    public String getFooterPath() {
        return footerPath;
    }

    public String getSoc() {
        return soc;
    }
}
