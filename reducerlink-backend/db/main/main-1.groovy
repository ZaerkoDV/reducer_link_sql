databaseChangeLog(logicalFilePath: 'main-1') {
    changeSet id: '1', author: 'author', {
        createTable tableName: 'user', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'first_name', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'last_name', type: 'varchar(1024)', {
                constraints nullable: false
            }

            column name: 'middle_name', type: 'varchar(255)', {
                constraints nullable: true
            }

            column name: 'birth', type: 'bigint', {
                constraints nullable: false
            }

            column name: 'status', type: 'varchar(64)', {
                constraints nullable: false
            }
        }

        createIndex tableName: 'user', indexName: 'idx__user__first_name', {
            column name: 'first_name'
        }

        createIndex tableName: 'user', indexName: 'idx__user__last_name', {
            column name: 'last_name'
        }

        createIndex tableName: 'user', indexName: 'idx__user__middle_name', {
            column name: 'middle_name'
        }

        createIndex tableName: 'user', indexName: 'idx__user__birth', {
            column name: 'birth'
        }

        createIndex tableName: 'user', indexName: 'idx__user__status', {
            column name: 'status'
        }

        rollback {
            dropTable tableName: 'user'
        }
    }

    changeSet id: '2', author: 'author', {
        createTable tableName: 'user_corespondence', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'email', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'skype', type: 'varchar(1024)', {
                constraints nullable: false
            }

            column name: 'phone', type: 'varchar(255)', {
                constraints nullable: true
            }

            column name: 'ip_address', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'user_id', type: 'bigint', {
                constraints nullable: false
            }
        }

        createIndex tableName: 'user_corespondence', indexName: 'idx__user_corespondence__email', {
            column name: 'email'
        }

        createIndex tableName: 'user_corespondence', indexName: 'idx__user_corespondence__skype', {
            column name: 'skype'
        }

        createIndex tableName: 'user_corespondence', indexName: 'idx__user_corespondence__phone', {
            column name: 'phone'
        }

        createIndex tableName: 'user_corespondence', indexName: 'idx__user_corespondence__ip_address', {
            column name: 'ip_address'
        }

        createIndex tableName: 'user_corespondence', indexName: 'idx__user_corespondence__user_id', {
            column name: 'user_id'
        }

        rollback {
            dropTable tableName: 'user_corespondence'
        }
    }

    changeSet(id: '3', author: 'author') {
        addForeignKeyConstraint(
            baseColumnNames: 'user_id',
            baseTableName: 'user_corespondence',
            constraintName: 'user_fk_in_user_corespondence',
            deferrable: 'false',
            initiallyDeferred: 'false',
            onDelete: 'CASCADE',
            onUpdate: 'CASCADE',
            referencedColumnNames: 'id',
            referencedTableName: 'user'
        )
    }

    changeSet id: '4', author: 'author', {
        createTable tableName: 'user_photo', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'created_at_timestamp', type: 'bigint', {
                constraints nullable: false
            }

            column name: 'photo_data', type: 'longblob', {
                constraints nullable: true
            }

            column name: 'user_id', type: 'bigint', {
                constraints nullable: false
            }
        }

        createIndex tableName: 'user_photo', indexName: 'idx__user_photo__created_at_timestamp', {
            column name: 'created_at_timestamp'
        }

        createIndex tableName: 'user_photo', indexName: 'idx__user_photo__photo_data', {
            column name: 'photo_data'
        }

        createIndex tableName: 'user_photo', indexName: 'idx__user_photo__user_id', {
            column name: 'user_id'
        }

        rollback {
            dropTable tableName: 'user_photo'
        }
    }

    changeSet(id: '5', author: 'author') {
        addForeignKeyConstraint(
            baseColumnNames: 'user_id',
            baseTableName: 'user_photo',
            constraintName: 'user_fk_in_user_photo',
            deferrable: 'false',
            initiallyDeferred: 'false',
            onDelete: 'CASCADE',
            onUpdate: 'CASCADE',
            referencedColumnNames: 'id',
            referencedTableName: 'user'	
        )
    }

    changeSet id: '6', author: 'author', {
        createTable tableName: 'user_security', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'login', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'password', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'role', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'token', type: 'varchar(255)', {
                constraints nullable: true
            }

            column name: 'user_id', type: 'bigint', {
                constraints nullable: true
            }
        }

        createIndex tableName: 'user_security', indexName: 'idx__user_security__login', {
            column name: 'login'
        }

        createIndex tableName: 'user_security', indexName: 'idx__user_security__password', {
            column name: 'password'
        }

        createIndex tableName: 'user_security', indexName: 'idx__user_security__role', {
            column name: 'role'
        }

        createIndex tableName: 'user_security', indexName: 'idx__user_security__tokin', {
            column name: 'token'
        }

        createIndex tableName: 'user_security', indexName: 'idx__user_security__user_id', {
            column name: 'user_id'
        }

        rollback {
            dropTable tableName: 'user_security'
        }
    }

    changeSet(id:'7', author: 'author') {
        addForeignKeyConstraint(
            baseColumnNames: 'user_id',
            baseTableName: 'user_security',
            constraintName: 'user_fk_in_user_security',
            deferrable: 'false',
            initiallyDeferred: 'false',
            onDelete: 'CASCADE',
            onUpdate: 'CASCADE',
            referencedColumnNames: 'id',
            referencedTableName: 'user'
        )
    }

    changeSet id: '8', author: 'author', {
        createTable tableName: 'link', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'tag', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'comment', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'short_url', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'full_url', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'user_id', type: 'bigint', {
                constraints nullable: true
            }
        }

        createIndex tableName: 'link', indexName: 'idx__link__tag', {
            column name: 'tag'
        }

        createIndex tableName: 'link', indexName: 'idx__link__comment', {
            column name: 'comment'
        }

        createIndex tableName: 'link', indexName: 'idx__link__short_url', {
            column name: 'short_url'
        }

        createIndex tableName: 'link', indexName: 'idx__link__full_url', {
            column name: 'full_url'
        }

        createIndex tableName: 'link', indexName: 'idx__link__user_id', {
            column name: 'user_id'
        }

        rollback {
            dropTable tableName: 'link'
        }
    }

    changeSet(id: '9', author: 'author') {
        addForeignKeyConstraint(
            baseColumnNames: 'user_id',
            baseTableName: 'link',
            constraintName: 'user_fk_in_link',
            deferrable: 'false',
            initiallyDeferred: 'false',
            onDelete: 'CASCADE',
            onUpdate: 'CASCADE',
            referencedColumnNames: 'id',
            referencedTableName: 'user'
        )
    }

    changeSet id: '10', author: 'author', {
        createTable tableName: 'link_history', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'created_at_timestamp', type: 'bigint', {
                constraints nullable: false
            }

            column name: 'sum_click', type: 'bigint', {
                constraints nullable: false
            }

            column name: 'link_id', type: 'bigint', {
                constraints nullable: true
            }
        }

        createIndex tableName: 'link_history', indexName: 'idx__link_history__created_at_timestamp', {
            column name: 'created_at_timestamp'
        }

        createIndex tableName: 'link_history', indexName: 'idx__link_history__sum_click', {
            column name: 'sum_click'
        }

        createIndex tableName: 'link_history', indexName: 'idx__link_history__link_id', {
            column name: 'link_id'
        }

        rollback {
            dropTable tableName: 'link'
        }
    }

    changeSet(id: '11', author: 'author') {
        addForeignKeyConstraint(
            baseColumnNames: 'link_id',
            baseTableName: 'link_history',
            constraintName: 'link_fk_in_link_history',
            deferrable: 'false',
            initiallyDeferred: 'false',
            onDelete: 'CASCADE',
            onUpdate: 'CASCADE',
            referencedColumnNames: 'id',
            referencedTableName: 'link'	
        )
    }
}
