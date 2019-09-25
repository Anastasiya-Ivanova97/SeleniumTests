package Company1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<String> lines = new ArrayList<>();
    private List<Company> all = new ArrayList<>();

    public void run() {
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

    private void parse() {
        System.out.println("Введите путь до json-файла");
        File file = new File(new Scanner(System.in).next());
        try {
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index = 2;

        boolean hasNextCompany = true;

        while (hasNextCompany) {
            hasNextCompany = hasNext(index);

            int totalId = getInt(index);
            String totalCode = getValue(index + 1);
            String totalNameFull = getValue(index + 2);
            String totalNameShort = getValue(index + 3);
            String inn = getValue(index + 4);
            int typeId = getInt(index + 6);
            String typeNameShort = getValue(index + 7);
            String typeNameFull = getLast(index + 8);
            String ogrn = getValue(index + 10);
            String egrul_date = getValue(index + 11);
            int countryId = getInt(index + 13);
            String countryCode = getValue(index + 14);
            String countryName = getLast(index + 15);
            String fioHead = getValue(index + 17);
            String address = getValue(index + 18);
            String phone = getValue(index + 19);
            String eMail = getValue(index + 20);
            String www = getValue(index + 21);
            boolean isResident = Boolean.parseBoolean(lines.get(index + 22).substring(
                    lines.get(index + 22).indexOf(':') + 3, lines.get(index + 22).length() - 1));

            CompanyType companyType = new CompanyType(typeId, typeNameShort, typeNameFull);
            Country country = new Country(countryId, countryCode, countryName);
            LocalDateTime date = parseDateReverse(egrul_date);

            Company company = new Company(totalId, totalCode, totalNameFull, totalNameShort, inn, companyType, ogrn, date, country,
                    fioHead, address, phone, eMail, www, isResident);

            index += 25;

            List<Security> securities = new ArrayList<>();

            boolean hasNextSecurity = true;

            while (hasNextSecurity) {
                hasNextSecurity = hasNext(index);
                int securityId = getInt(index);
                String securityCode = getValue(index + 1);
                String securityNameFull = getValue(index + 2);
                String securityCfi = getValue(index + 3);
                String securityDateTo = getValue(index + 4);
                String securityStateRegDate = getValue(index + 5);
                int stateId = getInt(index + 7);
                String stateName = getLast(index + 8);
                int currencyId = getInt(index + 11);
                String currencyCode = getValue(index + 12);
                String currencyNameShort = getValue(index + 13);
                String currencyNameFull = getLast(index + 14);

                LocalDateTime dateTo = parseDateReverse(securityDateTo);
                LocalDateTime regDate = parseDateReverse(securityStateRegDate);

                State state = new State(stateId, stateName);
                Currency currency = new Currency(currencyId, currencyCode, currencyNameShort, currencyNameFull);

                Security security = new Security(securityId, securityCode, securityNameFull, securityCfi, dateTo, regDate,
                        state, currency);

                securities.add(security);

                index += 18;
            }

            company.setSecurities(securities);
            all.add(company);
            index += 3;
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

    private String getValue(int index) {
        String line = lines.get(index);
        return line.substring(line.indexOf(':') + 3, line.length() - 2);
    }

    private int getInt(int index) {
        String line = lines.get(index);
        return Integer.parseInt(line.substring(line.indexOf(':') + 2, line.length() - 1));
    }

    private String getLast(int index) {
        String line = lines.get(index);
        return line.substring(line.indexOf(':') + 3, line.length() - 1);
    }

    private boolean hasNext(int startIndex) {
        int count = 1;
        for (int i = startIndex + 1; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '{') {
                    count++;
                }
                if (lines.get(i).charAt(j) == '}') {
                    count--;
                }
                if (count == 0) {
                    return (j + 1) < lines.get(i).length() && lines.get(i).charAt(j + 1) == ',';
                }
            }
        }
        return false;
    }
}
