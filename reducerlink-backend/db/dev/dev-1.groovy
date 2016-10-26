databaseChangeLog(logicalFilePath: 'dev-1') {
    changeSet id: '1', author: 'author', {
        insert tableName: 'user', {
            column name: 'id', value: 1
            column name: 'first_name', value: 'Ivan'
			column name: 'last_name', value: 'Ivanov'
			column name: 'middle_name', value: 'Ivanovich'
			column name: 'birth', value: 1468330053
			column name: 'status', value: 'active'
        }
		
		insert tableName: 'user', {
            column name: 'id', value: 2
            column name: 'first_name', value: 'Denis'
			column name: 'last_name', value: 'Petrovich'
			column name: 'middle_name', value: 'Maksimov'
			column name: 'birth', value: 1468330065
			column name: 'status', value: 'active'
        }

        rollback {
            delete tableName: 'user', where: 'id in (1,2)'
        }
    }
	
	changeSet id: '2', author: 'author', {
        insert tableName: 'user_corespondence', {
		    column name: 'id', value: 1
            column name: 'email', value: 'ivanov@gmail.com'
            column name: 'skype', value: 'ivanov.ivan'
			column name: 'phone', value: '123456789'
			column name: 'ip_address', value: '143.22.123.23'
			column name: 'user_id', value: 1
        }
		
		insert tableName: 'user_corespondence', {
		    column name: 'id', value: 2
            column name: 'email', value: 'petrovich@gmail.com'
            column name: 'skype', value: 'petrovch.denis'
			column name: 'phone', value: '987654321'
			column name: 'ip_address', value: '12.32.3.76'
			column name: 'user_id', value: 2
        }

        rollback {
            delete tableName: 'user_corespondence', where: 'id in (1,2)'
        }
    }
	
	changeSet id: '3', author: 'author', {
        insert tableName: 'user_photo', {
		    column name: 'id', value: 1
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'photo_data', value:''
			column name: 'user_id', value: 1
        }
		
		insert tableName: 'user_photo', {
		    column name: 'id', value: 2
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'photo_data', value:''
			column name: 'user_id', value: 2
        }

        rollback {
            delete tableName: 'user_photo', where: 'id in (1,2)'
        }
    }
	
	changeSet id: '4', author: 'author', {
        insert tableName: 'user_security', {
		    column name: 'id', value: 1
            column name: 'login', value: 'ivanov'
            column name: 'password', value: 'ivanov'
			column name: 'role', value: 'admin'
			column name: 'token', value: 'dfagshagdjbhgfdyvbfybvyvyvynhnfdnlvlfndjnfvdjlnvl'
			column name: 'user_id', value: 1
        }
		
		insert tableName: 'user_security', {
		    column name: 'id', value: 2
            column name: 'login', value: 'petrov'
            column name: 'password', value: 'petrov'
			column name: 'role', value: 'client'
			column name: 'token', value: 'werreriurihunchmehcobgubvnlvgbgbmugnbgnbugnnbgunbu'
			column name: 'user_id', value: 2
        }

        rollback {
            delete tableName: 'user_security', where: 'id in (1,2)'
        }
    }
	
	changeSet id: '5', author: 'author', {
        insert tableName: 'link', {
		    column name: 'id', value: 1
            column name: 'tag', value: 'google'
            column name: 'comment', value: 'Google search engine'
			column name: 'short_url', value: 'https://www.gogl.com/'
			column name: 'full_url', value: 'https://www.google.com/'
			column name: 'user_id', value: 1
        }
		
		insert tableName: 'link', {
		    column name: 'id', value: 2
            column name: 'tag', value: 'bring'
            column name: 'comment', value: 'Bring search engine'
			column name: 'short_url', value: 'http://www.brg.com/'
			column name: 'full_url', value: 'http://www.bing.com/'
			column name: 'user_id', value: 2
        }

        rollback {
            delete tableName: 'link', where: 'id in (1,2)'
        }
    }
	
	changeSet id: '6', author: 'author', {
        insert tableName: 'link_history', {
		    column name: 'id', value: 1
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'sum_click', value: 1
			column name: 'link_id', value: 1
        }
		
		insert tableName: 'link_history', {
		    column name: 'id', value: 2
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'sum_click', value: 100
			column name: 'link_id', value: 2
        }

        rollback {
            delete tableName: 'link', where: 'id in (1,2)'
        }
    }
}