package com.gmail.erofeev.st.alexei.thirdweek.repository;

import java.sql.Connection;

public interface DataBaseInitRepository {
    void init(Connection connection, String[] queries);
}
