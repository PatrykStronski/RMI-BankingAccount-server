package Pkg;

public interface MasterBus {
    public String registerUser(String login, String password);
    public String authorize(String login, String password);
    public int depositText(String text, String token);
    public String fetchText(int nmb, String token);
    public boolean logOff(String Token);
}
