package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TodoModel {
    public List<String> id = new ArrayList<>();
    public List<String> work = new ArrayList<>();
    public List<String> cond = new ArrayList<>();
    public List<String> memo = new ArrayList<>();
    public List<String> startDate = new ArrayList<>();
    public List<String> endDate = new ArrayList<>();

    public void select() throws Exception {

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

        Connection con = null;

        try {

            /*クラスローダによりJDBCドライバを読み込んでいることを示している。
            引数は、データベースにアクセスするためのJDBCドライバのクラス名である。*/
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            /*DriverManagerクラスのgetConnectionメソッドを使ってデータベースに接続する。*/
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connected....");

            /*データベースの接続後に、sql文をデータベースに直接渡すのではなく、
            sqlコンテナの役割を果たすオブジェクトに渡すためのStatementオブジェクトを作成する。*/
            Statement st = con.createStatement();

            /*SQL文を作成する*/
            String sqlStr = "SELECT * FROM todo";

            /*SQL文を実行した結果セットをResultSetオブジェクトに格納している*/
            ResultSet result = st.executeQuery(sqlStr);

            /*クエリ結果を1レコードずつ出力していく*/
            while (result.next()) {
                /*getString()メソッドは、引数に指定されたフィールド名(列)の値をStringとして取得する*/
                id.add(result.getString("id"));
                work.add(result.getString("work"));
                cond.add(result.getString("cond"));
                memo.add(result.getString("memo"));
                startDate.add(result.getString("estimated_start_date"));
                endDate.add(result.getString("esitmated_end_date"));
                System.out.println(id.get(id.size()-1) + ", "
                        + work.get(work.size()-1) + ", "
                        + cond.get(cond.size()-1) + ","
                        + memo.get(memo.size()-1) + ","
                        + startDate.get(startDate.size()-1) + ","
                        + endDate.get(endDate.size()-1));
            }

            /*ResultSetオブジェクトを閉じる*/
            result.close();

            /*Statementオブジェクトを閉じる*/
            st.close();

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

    public void insert() throws Exception {
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

        Connection con = null;

        try {

            /*クラスローダによりJDBCドライバを読み込んでいることを示している。
            引数は、データベースにアクセスするためのJDBCドライバのクラス名である。*/
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            /*DriverManagerクラスのgetConnectionメソッドを使ってデータベースに接続する。*/
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connected....");

            /*データベースの接続後に、sql文をデータベースに直接渡すのではなく、
            sqlコンテナの役割を果たすオブジェクトに渡すためのStatementオブジェクトを作成する。*/
            Statement st = con.createStatement();

            /*SQL文を作成する*/
            //String sqlStr = "insert into todo.todo (work, cond, memo, estimated_start_date, esitmated_end_date, create_date, update_date) values('',0,'','','','','')";
            PreparedStatement pstmt = con.prepareStatement("insert into todo.todo (work, cond, memo, estimated_start_date, esitmated_end_date, create_date, update_date) values(?,?,?,?,?,?,?)");

            /*SQL文を実行した結果セットをResultSetオブジェクトに格納している*/
            //st.executeUpdate(sqlStr);
            pstmt.setString(1, "");
            pstmt.setInt(2, 1);
            pstmt.setString(3, "");
            pstmt.setDate(4, new Date(System.currentTimeMillis()));
            pstmt.setDate(5, new Date(System.currentTimeMillis()));
            pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();

            /*Statementオブジェクトを閉じる*/
            st.close();

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

    public void update() {

    }
}
