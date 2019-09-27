import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class App {
    private List<Company> all = new ArrayList<>();

    void run() {
        boolean running = true;
        while(running) {
            boolean validFile = false;

            while(!validFile) {
                System.out.println("Введите путь до json-файла. " +
                        "Напишите quit, чтобы закончить");
                String filename = new Scanner(System.in).next();

                if (filename.equals("quit")) {
                    System.out.println("Заканчиваем работу...");
                    running = false;
                    break;
                }

                File file = new File(filename);
                if (!file.exists()) {
                    System.out.println("Такого файла не сущетсвует.");
                    continue;
                }
                parse(filename);
                printInfo();
                printFinishedSecurities();
                validFile = true;
            }
            while(running) {
                System.out.println("Введите дату в формате dd.mm.yyyy. Напишите quit " +
                        "чтобы выбрать другой файл:");
                String input = new Scanner(System.in).next();
                if (input.equals("quit")) {
                    break;
                }
                printCompaniesAfterDate(input);
                System.out.println("Введите название валюты USD/EUR/RUB. Напишите quit " +
                        "чтобы выбрать другой файл:");
                String currency = new Scanner(System.in).next();
                if (currency.equals("quit")) {
                    break;
                }
                printSecuritiesByCurrency("USD");
            }
        }
    }

    private void printInfo() {
        all.forEach(Company::printInfo);
    }

    private void printFinishedSecurities() {
        all.forEach(Company::printFinishedSecurities);
        int count = all.stream().mapToInt(Company::getFinishedSecuritiesCount).sum();
        System.out.println("Количество: " + count);
    }

    private void printCompaniesAfterDate(String dateString) {
        Date date = parseDate(dateString);
        all.stream()
                .filter(company -> company.hasCreatedAfterDate(date))
                .forEach(Company::printInfo);
    }

    private void printSecuritiesByCurrency(String currency) {
        all.forEach(company -> company.printInfoByCode(currency));
    }

    private Company parseCompany(JSONObject c){

        long totalId = (Long)c.get("id");
        String totalCode = (String)c.get("code");
        String totalNameFull = (String)c.get("name_full");
        String totalNameShort = (String)c.get("name_short");
        String inn = (String)c.get("inn");

        JSONObject jtype = (JSONObject) c.get("company_type");
        long typeId = (Long)jtype.get("id");
        String typeNameShort = (String)jtype.get("name_short");
        String typeNameFull = (String)jtype.get("name_full");

        String ogrn = (String)c.get("ogrn");
        String egrul_date = (String)c.get("egrul_date");

        JSONObject jcountry = (JSONObject) c.get("country");
        long countryId =(Long) jcountry.get("id");
        String countryCode =(String) jcountry.get("code");
        String countryName =(String) jcountry.get("name");

        String fioHead = (String)c.get("fio_head");
        String address =(String) c.get("address");
        String phone = (String)c.get("phone");
        String eMail = (String)c.get("e_mail");
        String www = (String)c.get("www");
        boolean isResident = (Boolean)c.get("is_resident");
        CompanyType companyType = new CompanyType((int)typeId, typeNameShort, typeNameFull);
        Country country = new Country((int)countryId, countryCode, countryName);
        Date date = parseDateReverse(egrul_date);

        Company company = new Company((int)totalId, totalCode, totalNameFull, totalNameShort, inn, companyType, ogrn, date, country,
                fioHead, address, phone, eMail, www, isResident);

        JSONArray jsecurities = (JSONArray) c.get("securities");
        addSecurities(company, jsecurities);

        return company;
    }

    private void addSecurities(Company company, JSONArray jsecurities){
        List<Security> securities = new ArrayList<>();
        for(Object o: jsecurities) {
            JSONObject sec= (JSONObject)o;
            long securityId = (Long)sec.get("id");
            String securityCode = (String)sec.get("code");
            String securityNameFull = (String)sec.get("name_full");
            String securityCfi = (String)sec.get("cfi");
            String securityDateTo = (String)sec.get("date_to");
            String securityStateRegDate = (String)sec.get("state_reg_date");

            JSONObject jstate = (JSONObject) sec.get("state");
            long stateId = (Long)jstate.get("id");
            String stateName = (String)jstate.get("name");

            JSONObject jcurrency = (JSONObject) sec.get("currency");
            long currencyId = (Long)jcurrency.get("id");
            String currencyCode = (String)jcurrency.get("code");
            String currencyNameShort = (String)jcurrency.get("name_short");
            String currencyNameFull = (String)jcurrency.get("name_full");

            Date dateTo = parseDateReverse(securityDateTo);
            Date regDate = parseDateReverse(securityStateRegDate);

            State state = new State((int)stateId, stateName);
            Currency currency = new Currency((int)currencyId, currencyCode, currencyNameShort, currencyNameFull);

            Security security = new Security((int)securityId, securityCode, securityNameFull, securityCfi, dateTo, regDate,
                    state, currency);

            securities.add(security);
        }
        company.setSecurities(securities);

    }

    private void parse(String filename) {
        JSONParser jsonParser = new JSONParser();
        FileReader reader;
        try {
            reader = new FileReader(filename);
            JSONArray root = (JSONArray) jsonParser.parse(reader);
            root.forEach(company -> all.add(parseCompany((JSONObject) company)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Date parseDateReverse(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date d = null;
        try {
            d = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    private Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
        Date d = null;
        try {
            d = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
