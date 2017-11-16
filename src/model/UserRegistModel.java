package model;

import service.SafePassword;

import java.sql.*;

public class UserRegistModel {
    public int id;
    public String mail;
    public String name;
    public String password;

    public void insert() throws Exception {
        Connection con = null;

        try {

            // DB接続情報取得
            con = dbConnect();

            System.out.println("Connected....");

            /*データベースの接続後に、sql文をデータベースに直接渡すのではなく、
            sqlコンテナの役割を果たすオブジェクトに渡すためのPreparedStatementオブジェクトを作成する。*/
            /*SQL文を作成する*/
            PreparedStatement pstmt = con.prepareStatement("insert into todo.user (mail, name, password) values(?,?,?)");

            /*SQL文を実行した結果セットをResultSetオブジェクトに格納している*/
            //st.executeUpdate(sqlStr);
            pstmt.setString(1, mail);
            pstmt.setString(2, name);
            pstmt.setString(3, SafePassword.getStretchedPassword(mail, password));
            pstmt.executeUpdate();

            /*PreparedStatementオブジェクトを閉じる*/
            pstmt.close();

            /*Connectionオブジェクトを閉じる*/
            con.close();
        } catch (SQLException e) {

            /*エラーメッセージ出力*/
            System.out.println("Connection Failed. : " + e.toString());

            /*例外を投げちゃうぞ*/
            throw new Exception();

        } catch (ClassNotFoundException e) {

            /*エラーメッセージ出力*/
            System.out.println("ドライバを読み込めませんでした " + e);
        } catch (Exception e) {
            /*エラーメッセージ出力*/
            System.out.println("予期せぬ例外が発生しました " + e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

                /*エラーメッセージ出力*/
                System.out.println("Exception2! :" + e.toString());

                /*例外を投げちゃうぞ*/
                throw new Exception();
            }
        }

    }

    public void getId() throws Exception {
        Connection con = null;

        try {

            // DB接続情報取得
            con = dbConnect();

            System.out.println("Connected....");

            /*データベースの接続後に、sql文をデータベースに直接渡すのではなく、
            sqlコンテナの役割を果たすオブジェクトに渡すためのPreparedStatementオブジェクトを作成する。*/
            /*SQL文を作成する*/
            PreparedStatement pstmt = con.prepareStatement("select * from todo.user where mail=? and password=?");

            /*SQL文を実行した結果セットをResultSetオブジェクトに格納している*/
            pstmt.setString(1, mail);
            pstmt.setString(2, password);
            pstmt.executeQuery();

            if (pstmt.getResultSet().next()) {
                id = pstmt.getResultSet().getInt("userId");
            }

            /*Statementオブジェクトを閉じる*/
            pstmt.close();

            /*Connectionオブジェクトを閉じる*/
            con.close();
        } catch (SQLException e) {

            /*エラーメッセージ出力*/
            System.out.println("Connection Failed. : " + e.toString());

            /*例外を投げちゃうぞ*/
            throw new Exception();

        } catch (ClassNotFoundException e) {

            /*エラーメッセージ出力*/
            System.out.println("ドライバを読み込めませんでした " + e);
        } catch (Exception e) {
            /*エラーメッセージ出力*/
            System.out.println("予期せぬ例外が発生しました " + e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

                /*エラーメッセージ出力*/
                System.out.println("Exception2! :" + e.toString());

                /*例外を投げちゃうぞ*/
                throw new Exception();
            }
        }

    }

    private Connection dbConnect() throws Exception {
        /*接続先サーバー名を"localhost"で与えることを示している*/
        String servername = "localhost";

        /*接続するデータベース名をsenngokuとしている*/
        String databasename = "todo";

        /*データベースの接続に用いるユーザ名をrootユーザとしている*/
        String user = "dbuser";

        /*データベースの接続に用いるユーザのパスワードを指定している*/
        String password = "DBuser01+";

        /*取り扱う文字コードをUTF-8文字としている*/
        String serverencoding = "UTF-8";

        /*データベースをあらわすURLを設定している*/
        String url = "jdbc:mysql://localhost/" + databasename;

        /*クラスローダによりJDBCドライバを読み込んでいることを示している。
            引数は、データベースにアクセスするためのJDBCドライバのクラス名である。*/
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        /*DriverManagerクラスのgetConnectionメソッドを使ってデータベースに接続する。*/
        return DriverManager.getConnection(url, user, password);
    }

}
