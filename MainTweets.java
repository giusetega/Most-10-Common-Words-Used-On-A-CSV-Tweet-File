package lesson15;

public class MainTweets {

    public static void main(String[] args) {
        Tweets tweets = new Tweets();
        tweets.readFile("realdonaltrump.csv");
        tweets.printTreeMap();
    }

}
