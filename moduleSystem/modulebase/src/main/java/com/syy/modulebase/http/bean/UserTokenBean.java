package com.syy.modulebase.http.bean;

/**
 * @Description：token
 * @Author：Sai
 * @Date：2019/3/15 11:20
 */
public class UserTokenBean extends HttpResult {

    /**
     * access_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtc2ciOiJzdWNjZXNzIiwibGljZW5zZSI6Im1hZGUgYnkgZ2FjLW5pbyIsImNvZGUiOjAsImRhdGEiOm51bGwsInVzZXJfbmFtZSI6IjE4OTI5Mjk2MzM1Iiwic2NvcGUiOlsic2VydmVyIl0sImV4cCI6MTU1MjY3NzY4OSwidXNlcklkIjozMCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiYXBwX3NwX3JvbGUiXSwianRpIjoiZDM4ZTcwYTEtNWYyMC00YmQ5LTkzOTYtNzI3MmE3MjJiZTMxIiwiY2xpZW50X2lkIjoiZ2FjIn0.0LeggBX_J1Hx9EM-SX2VnUcqWJ1jCpcJDdtrsqpbbzQ
     * token_type : bearer
     * refresh_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtc2ciOiJzdWNjZXNzIiwibGljZW5zZSI6Im1hZGUgYnkgZ2FjLW5pbyIsImNvZGUiOjAsImRhdGEiOm51bGwsInVzZXJfbmFtZSI6IjE4OTI5Mjk2MzM1Iiwic2NvcGUiOlsic2VydmVyIl0sImF0aSI6ImQzOGU3MGExLTVmMjAtNGJkOS05Mzk2LTcyNzJhNzIyYmUzMSIsImV4cCI6MTU1NTIyNjQ4OSwidXNlcklkIjozMCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiYXBwX3NwX3JvbGUiXSwianRpIjoiZjIyMTNjNDgtYTVmZS00N2UzLWEzYmQtZmQ1ZWE0OGI5OTUwIiwiY2xpZW50X2lkIjoiZ2FjIn0.gqFwJtXKq6pn38FSYcALhqVZNoda2zd6GmmBG-9Sa0c
     * expires_in : 42944
     * scope : server
     * license : made by gac-nio
     * data : null
     * userId : 30
     * jti : d38e70a1-5f20-4bd9-9396-7272a722be31
     */

    private String access_token;
    private String refresh_token;
    private String userId;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
