package com.collaborativeFilter.userBasedCF;

import java.util.*;

/**
 * 协同过滤 用户推荐简单实现
 */
public class SimpleUserCF {

    private static String[] users={"A", "B", "C", "D", "E"};
    private static String[] movies={"film1", "film2", "film3", "film4", "film5", "film6", "film7"};

    private static int[][] allUserRatingList={
            {3,1,4,4,1,0,0},
            {0,5,1,0,0,4,0},
            {1,0,5,4,3,5,2},
            {3,1,4,3,5,0,0},
            {5,2,0,1,0,5,5}};

    private static List<List<Object>> similarityUsers = null;
    private static List<String> targetRecomendMovies = null;
    private static List<String> commentedMovies = null;
    private static Integer ratingIndex = null;
    private static Integer targetUserIndex = null;

    private static void calculateRecommendMovie(){
        targetRecomendMovies = new ArrayList<String>();
        List<List<Object>> recommendMovies = new ArrayList<List<Object>>();
        List<Object> recommendMovie = null;
        double recommendRate = 0;
        double sumRate = 0;
        for(int i = 0; i < 7; i++){
            recommendMovie = new ArrayList<Object>();
            recommendMovie.add(i);
            recommendRate = allUserRatingList[Integer.parseInt(similarityUsers.get(0).get(0).toString())][i]
                    * Double.parseDouble(similarityUsers.get(0).get(1).toString())
                    + allUserRatingList[Integer.parseInt(similarityUsers.get(1).get(0).toString())][i]
                    * Double.parseDouble(similarityUsers.get(1).get(1).toString());
            recommendMovie.add(recommendRate);
            recommendMovies.add(recommendMovie);
            sumRate += recommendRate;
        }
        sortCollection(recommendMovies, -1);
        for(List item : recommendMovies){
            if(Double.parseDouble(item.get(1).toString()) > sumRate / 7){
                targetRecomendMovies.add(movies[Integer.parseInt(item.get(0).toString())]);
            }
        }
    }

    /**
     * 获取2个最相似的用户
     */
    private static void calculateUsersSimilarity(){
        similarityUsers = new ArrayList<List<Object>>();
        List<List<Object>> userSimilaritys = new ArrayList<List<Object>>();
        for(int i = 0; i < 5; i++){
            if(i == targetUserIndex){
                continue;
            }
            List<Object> userSimilarity = new ArrayList<Object>();
            userSimilarity.add(i);
            userSimilarity.add(calculateTwoUsersSimilarity(allUserRatingList[i], allUserRatingList[targetUserIndex]));
            userSimilaritys.add(userSimilarity);
        }
        sortCollection(userSimilaritys, 1);

        similarityUsers.add(userSimilaritys.get(0));
        similarityUsers.add(userSimilaritys.get(1));
    }

    private static void handleRecommendMovies(){
        commentedMovies = new ArrayList<String>();
        for(int i = 0; i < allUserRatingList[targetUserIndex].length; i++){
            if(allUserRatingList[targetUserIndex][i] != 0){
                commentedMovies.add(movies[i]);
            }
        }
    }

    /**
     * 计算两个用户的相似度，这里使用欧氏距离
     * @param ratingsSource
     * @param ratingsTarget
     * @return
     */
    private static double calculateTwoUsersSimilarity(int[] ratingsSource, int[] ratingsTarget){
        float sum = 0;
        for(int i = 0; i < 7; i++){
            sum += Math.pow(ratingsSource[i] - ratingsTarget[i], 2);
        }
        return Math.sqrt(sum);
    }

    private static Integer getUserIndex(String user){
        if(user == null || "".equals(user)){
            return null;
        }
        for(int i = 0; i < users.length; i++){
            if(user.equals(users[i])){
                return i;
            }
        }
        return null;
    }

    /**
     *
     * @param list
     * @param order  1正序  -1倒序
     */
    private static void sortCollection(List<List<Object>> list, final int order){
        Collections.sort(list, new Comparator<List<Object>>() {
            public int compare(List<Object> o1, List<Object> o2) {
                if(Double.valueOf(o1.get(1).toString()) > Double.valueOf(o2.get(1).toString())){
                    return order;
                }else if(Double.valueOf(o1.get(1).toString()) < Double.valueOf(o2.get(1).toString())){
                    return -order;
                }
                return 0;
            }
        });
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        while(user != null && !"exit".equals(user)){
            targetUserIndex = getUserIndex(user);
            if(targetUserIndex == null){
                System.out.println("无此用户，请重新输入！");
            }else {
                calculateUsersSimilarity();
                calculateRecommendMovie();
                handleRecommendMovies();
                System.out.println("推荐电影列表： ");
                for(String item : targetRecomendMovies){
                    if(!commentedMovies.contains(item)){
                        System.out.println(item + "   ");
                    }
                }
                System.out.println();
            }
            user = scanner.nextLine();
            targetRecomendMovies = null;
        }

    }


}
