package com.codetally.configuration;

import com.codetally.repository.BaseRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by greg on 29/08/17.
 */
@WebListener
public class ServletContextInitialization  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
        Connection connection = BaseRepository.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            System.out.println("Creating Table charge");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS charge (chargeid bigserial primary key, event character varying(255), action character varying(255), chargetype character varying(255), chargeref text, description text, calculationtype character varying(255), chargeamount numeric(19,2), repoid integer)");
            System.out.println("Creating Table commit_files");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS commit_files (sha text, filepath text, file_action integer)");
            System.out.println("Creating Table commits");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS commits (sha character varying(255), author_name character varying(255), author_email character varying(255),message text, commit_date character varying(255), repoid bigint, codecost numeric(19,2), elapsed_time character varying(25), commitid bigserial primary key, commit_url text, calculation_date character varying(255))");
            System.out.println("Creating Table hourly_rates");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS hourly_rates (author_email character varying(1024), hourly_rate numeric(19,2), description text, repoid bigint)");
            System.out.println("Creating Table logline");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS logline (logid bigserial primary key,level character varying(255),message text,\"timestamp\" character varying(255),repoid bigint)");
            System.out.println("Creating Table repo");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS repo (repoid bigserial primary key,owner character varying(255),repo_name character varying(1024),codecost numeric(19,2),currency_code character varying(3),repo_url text)");
            System.out.println("Create table complete.");
            // First time use of historical system. In this case, dropping previously created commit_files table.
            stmt.executeUpdate("DROP TABLE IF EXISTS commit_files");
            System.out.println("Drop table commit_files is complete.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting Down!");
    }
}
