/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.parsing.parser.statement.ddl.truncate;

import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.constant.DatabaseType;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.LexerEngine;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.mysql.sql.MySQLTruncateParser;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.oracle.sql.OracleTruncateParser;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.postgresql.sql.PostgreSQLTruncateParser;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.sqlserver.sql.SQLServerTruncateParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Truncate语句解析器工厂.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TruncateParserFactory {
    
    /**
     * 创建Truncate语句解析器.
     * 
     * @param dbType 数据库类型
     * @param shardingRule 分库分表规则配置
     * @param lexerEngine 解析器
     * @return Truncate语句解析器
     */
    public static AbstractTruncateParser newInstance(final DatabaseType dbType, final ShardingRule shardingRule, final LexerEngine lexerEngine) {
        switch (dbType) {
            case H2:
            case MySQL:
                return new MySQLTruncateParser(shardingRule, lexerEngine);
            case Oracle:
                return new OracleTruncateParser(shardingRule, lexerEngine);
            case SQLServer:
                return new SQLServerTruncateParser(shardingRule, lexerEngine);
            case PostgreSQL:
                return new PostgreSQLTruncateParser(shardingRule, lexerEngine);
            default:
                throw new UnsupportedOperationException(String.format("Cannot support database [%s].", dbType));
        }
    }
}
