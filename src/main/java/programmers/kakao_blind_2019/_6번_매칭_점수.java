package programmers.kakao_blind_2019;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _6번_매칭_점수 {
    public int solution(String word, String[] pages) {
        Map<String, WebPage> map = new HashMap<>();
        PriorityQueue<WebPage> pq = new PriorityQueue<>();
        int index = 0;

        for (String page : pages) {
            String homeUrl = getHomeUrl(page);
            int defaultScore = getDefaultScore(page.toLowerCase(), word.toLowerCase());
            List<String> externalLinks = getExternalUrls(page);
            WebPage webPage = new WebPage(index++, defaultScore, externalLinks);
            map.put(homeUrl, webPage);
        }

        for (String key : map.keySet()) {
            WebPage webPage = map.get(key);
            List<String> externalLinks = webPage.externalLinks;
            for (String link : externalLinks) {
                if (map.containsKey(link)) map.get(link).linkScore += webPage.addScore;
            }
        }

        for (String key : map.keySet()) {
            WebPage webPage = map.get(key);
            webPage.matchScore = webPage.defaultScore + webPage.linkScore;
            pq.add(webPage);
        }

        return pq.poll().index;
    }

    private int getDefaultScore(String page, String word) {
        int count = 0;
        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        Matcher matcher = pattern.matcher(page.replaceAll("[0-9]", " "));
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private String getHomeUrl(String page) {
        Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        Matcher matcher = pattern.matcher(page);

        boolean isFind = matcher.find();
        return isFind ? matcher.group().split("\"")[3] : "";
    }

    private List<String> getExternalUrls(String page) {
        List<String> externalLinks = new ArrayList<>();
        Pattern pattern = Pattern.compile("<a href=\"https://(\\S*)\"");
        Matcher matcher = pattern.matcher(page);
        while (matcher.find()) {
            externalLinks.add(matcher.group().split("\"")[1]);
        }
        return externalLinks;
    }

    public static class WebPage implements Comparable<WebPage> {
        int index;
        int defaultScore;
        List<String> externalLinks;
        int externalLinkCount;

        double addScore;
        double linkScore = 0;
        double matchScore = 0;

        public WebPage(int index, int defaultScore, List<String> externalLinks) {
            this.index = index;
            this.defaultScore = defaultScore;
            this.externalLinks = externalLinks;
            this.externalLinkCount = externalLinks.size();
            this.addScore = (1.0) * defaultScore / externalLinkCount;
        }

        @Override
        public int compareTo(WebPage o) {
            int matchCompare = Double.compare(o.matchScore, this.matchScore);
            if (matchCompare == 0) {
                return Integer.compare(this.index, o.index);
            } else {
                return matchCompare;
            }
        }
    }

    @Test
    void test() {
        String[] testPage = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        solution("blind", testPage);
    }
}
