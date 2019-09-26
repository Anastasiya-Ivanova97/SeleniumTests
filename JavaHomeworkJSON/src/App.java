import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    private List<Company> all = new ArrayList<>();

    void run() {
        parse();

        printInfo();
        printFinishedSecurities();
        printCompaniesAfterDate("01.01.2000");
        printSecuritiesByCurrency("USD");
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
        LocalDateTime date = parseDate(dateString);
        for (Company company : all) {
            if (company.hasCreatedAfterDate(date)) {
                company.printInfo();
            }
        }
    }

    private void printSecuritiesByCurrency(String currency) {
        for (Company company : all) {
            company.printInfoByCode(currency);
        }
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
        LocalDateTime date = parseDateReverse(egrul_date);

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

            LocalDateTime dateTo = parseDateReverse(securityDateTo);
            LocalDateTime regDate = parseDateReverse(securityStateRegDate);

            State state = new State((int)stateId, stateName);
            Currency currency = new Currency((int)currencyId, currencyCode, currencyNameShort, currencyNameFull);

            Security security = new Security((int)securityId, securityCode, securityNameFull, securityCfi, dateTo, regDate,
                    state, currency);

            securities.add(security);
        }
        company.setSecurities(securities);

    }

    private void parse() {
        System.out.println("Введите путь до json-файла");
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        FileReader reader;
        try
        {
            reader = new FileReader(new Scanner(System.in).next());
            JSONArray root =  (JSONArray)jsonParser.parse(reader);
            for(Object o: root) {
                all.add(parseCompany((JSONObject) o));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private LocalDateTime parseDateReverse(String date) {
        return LocalDateTime.of(
                Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8, 10)),
                0,
                0);
    }

    private LocalDateTime parseDate(String date) {
        int year = Integer.parseInt(date.substring(6));
        if (year < 1000) {
            year += 2000;
            if (year > 2050) {
                year -= 100;
            }
        }
        return LocalDateTime.of(
                year,
                Integer.parseInt(date.substring(3, 5)),
                Integer.parseInt(date.substring(0, 2)),
                0,
                0);
    }
}
