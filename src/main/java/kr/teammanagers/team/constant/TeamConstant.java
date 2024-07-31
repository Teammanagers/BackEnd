package kr.teammanagers.team.constant;

import java.util.Random;

public final class TeamConstant {

    private TeamConstant() {}
    public static final String DEFAULT_TEAM_PASSWORD = "1234";
    public static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final int BASE = BASE62.length();
    public static final Random RANDOM = new Random();
    public static final int PADDING = 8;

}
