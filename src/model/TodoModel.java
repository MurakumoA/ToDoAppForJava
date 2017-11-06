package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TodoModel {
    public String id;
    public String work;
    public String cond;
    public String memo;

    public void select() throws Exception{

        /*接続先サーバー名を"localhost"で与えることを示している*/
        String servername     = "localhost";

        /*接続するデータベース名をsenngokuとしている*/
        String databasename   = "todo";

        /*データベースの接続に用いるユーザ名をrootユーザとしている*/
        String user = "dbuser";

        /*データベースの接続に用いるユーザのパスワードを指定している*/
        String password = "DBuser01+";

        /*取り扱う文字コードをUTF-8文字としている*/
        String serverencoding = "UTF-8";

        /*データベースをあらわすURLを設定している*/
        String url =  "jdbc:mysql://localhost/" + databasename;

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
                id = result.getString("id");
                work = result.getString("work");
                cond = result.getString("cond");
                memo = result.getString("memo");
                System.out.println(id + ", " + work + ", " + cond + "," + memo);
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
        }
        finally{
            try{
                if( con != null ){
                    con.close();
                }
            }
            catch(Exception e){

                /*エラーメッセージ出力*/
                System.out.println( "Exception2! :" + e.toString() );

                /*例外を投げちゃうぞ*/
                throw new Exception();
            }
        }
    }
    public void insert() {

    }

    public void update() {

    }
}
