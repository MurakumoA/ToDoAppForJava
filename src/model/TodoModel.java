package model;

import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class TodoModel {
    private int userId;
    private List<String> id = new ArrayList<>();
    private List<String> work = new ArrayList<>();
    private List<String> cond = new ArrayList<>();
    private List<String> memo = new ArrayList<>();
    private List<String> startDate = new ArrayList<>();
    private List<String> endDate = new ArrayList<>();

    public void select() throws Exception {
        Connection con = null;

        id = new ArrayList<>();
        work = new ArrayList<>();
        cond = new ArrayList<>();
        memo = new ArrayList<>();
        startDate = new ArrayList<>();
        endDate = new ArrayList<>();

        try {
            // DB接続情報取得
            con = dbConnect();

            System.out.println("Connected....");

            /*データベースの接続後に、sql文をデータベースに直接渡すのではなく、
            sqlコンテナの役割を果たすオブジェクトに渡すためのStatementオブジェクトを作成する。*/
            /*SQL文を作成する*/
            PreparedStatement pstmt = con.prepareStatement("select * from todo.todo where userId=?");

            /*SQL文を実行した結果セットをResultSetオブジェクトに格納している*/
            pstmt.setInt(1, userId);
            pstmt.executeQuery();
            ResultSet result = pstmt.getResultSet();

            /*クエリ結果を1レコードずつ出力していく*/
            while (result.next()) {
                /*getString()メソッドは、引数に指定されたフィールド名(列)の値をStringとして取得する*/
                id.add(result.getString("id"));
                work.add(result.getString("work"));
                cond.add(result.getString("cond"));
                memo.add(result.getString("memo"));
                startDate.add(result.getString("estimated_start_date") == null? "":result.getString("estimated_start_date"));
                endDate.add(result.getString("esitmated_end_date") == null? "":result.getString("esitmated_end_date"));
            }

            /*ResultSetオブジェクトを閉じる*/
            result.close();

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

    public void regist() throws Exception {
        for (int i = 0; i < id.size(); i++) {
            if (id.get(i).isEmpty()) {
                insert(work.get(i), cond.get(i), memo.get(i), startDate.get(i), endDate.get(i));
            } else {
                update(id.get(i), work.get(i), cond.get(i), memo.get(i), startDate.get(i), endDate.get(i));
            }
        }
    }

    private void insert(String work, String cond, String memo, String startDate, String endDate) throws Exception {

        Connection con = null;

        try {

            // DB接続情報取得
            con = dbConnect();

            System.out.println("Connected....");

            /*データベースの接続後に、sql文をデータベースに直接渡すのではなく、
            sqlコンテナの役割を果たすオブジェクトに渡すためのPreparedStatementオブジェクトを作成する。*/
            /*SQL文を作成する*/
            PreparedStatement pstmt = con.prepareStatement("insert into todo.todo (userId, work, cond, memo, estimated_start_date, esitmated_end_date, create_date, update_date) values(?,?,?,?,?,?,?,?)");

            /*SQL文を実行した結果セットをResultSetオブジェクトに格納している*/
            pstmt.setInt(1, userId);
            pstmt.setString(2, work);
            pstmt.setInt(3, Integer.parseInt(cond));
            pstmt.setString(4, memo);
            pstmt.setString(5, "".equals(startDate)? null:startDate);
            pstmt.setString(6, "".equals(endDate)? null:endDate);
            pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
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

    private void update(String id, String work, String cond, String memo, String startDate, String endDate) throws Exception {

        Connection con = null;

        try {

            // DB接続情報取得
            con = dbConnect();


            System.out.println("Connected....");

            /*データベースの接続後に、sql文をデータベースに直接渡すのではなく、
            sqlコンテナの役割を果たすオブジェクトに渡すためのPreparedStatementオブジェクトを作成する。*/
            /*SQL文を作成する*/
            PreparedStatement pstmt = con.prepareStatement("update todo.todo set work=?, cond=?, memo=?, estimated_start_date=?, esitmated_end_date=?, update_date=? where id=?");

            /*SQL文を実行した結果セットをResultSetオブジェクトに格納している*/
            //st.executeUpdate(sqlStr);
            pstmt.setString(1, work);
            pstmt.setInt(2, Integer.parseInt(cond));
            pstmt.setString(3, memo);
            pstmt.setDate(4, Date.valueOf(startDate));
            pstmt.setDate(5, Date.valueOf(endDate));
            pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(7, Integer.parseInt(id));
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

    public void delete(String id) throws Exception {

        Connection con = null;

        try {

            // DB接続情報取得
            con = dbConnect();


            System.out.println("Connected....");

            /*データベースの接続後に、sql文をデータベースに直接渡すのではなく、
            sqlコンテナの役割を果たすオブジェクトに渡すためのPreparedStatementオブジェクトを作成する。*/
            /*SQL文を作成する*/
            PreparedStatement pstmt = con.prepareStatement("delete from todo.todo where id=?");

            /*SQL文を実行した結果セットをResultSetオブジェクトに格納している*/
            //st.executeUpdate(sqlStr);
            pstmt.setString(1, id);
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
