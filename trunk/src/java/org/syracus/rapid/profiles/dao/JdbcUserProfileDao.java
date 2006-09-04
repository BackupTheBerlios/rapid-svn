package org.syracus.rapid.profiles.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.syracus.rapid.profiles.UserProfile;

public class JdbcUserProfileDao extends JdbcDaoSupport implements
		IUserProfileDao {

	public void create(UserProfile userProfile) {
		try {
			getJdbcTemplate().update(
					"INSERT INTO user_profiles ( id, profile ) VALUES ( ?, ? )",
					new Object[]{
							userProfile.getId(),
							UserProfile.getPropertiesAsString( userProfile.getProperties() )
					}
			);
		} catch( IOException e ) {
			throw new ProfileDaoException( "Failed to create user profile.", e );
		}
	}

	public void delete(UserProfile userProfile) {
		getJdbcTemplate().update(
				"DELETE FROM user_profiles WHERE id = ?",
				new Object[]{
						userProfile.getId()
				}
		);
	}

	public UserProfile find(Long id) {
		return( (UserProfile)getJdbcTemplate().query(
				"SELECT id, profile FROM user_profiles WHERE id = ?",
				new Object[]{
						id
				},
				new ResultSetExtractor() {
					public Object extractData(ResultSet result) throws SQLException, DataAccessException {
						if ( result.next() ) {
							Long id = result.getLong( "id" );
							String data = result.getString( "profile" );
							
							UserProfile profile = new UserProfile();
							profile.setId( id );
							try {
								profile.setProperties( UserProfile.getPropertiesFromString( data ) );
							} catch( IOException e ) {
								throw new ProfileDaoException( "Failed to parse user profile data.", e );
							}
							
							return( profile );
						}
						return( null );
					}
					
				}
		) );
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll() {
		return( (List<UserProfile>)getJdbcTemplate().query(
				"SELECT id, profile FROM user_profiles WHERE id = ?",
				new ResultSetExtractor() {
					public Object extractData(ResultSet result) throws SQLException, DataAccessException {
						ArrayList<UserProfile> profiles = new ArrayList<UserProfile>();
						while ( result.next() ) {
							Long id = result.getLong( "id" );
							String data = result.getString( "profile" );
							
							UserProfile profile = new UserProfile();
							profile.setId( id );
							try {
								profile.setProperties( UserProfile.getPropertiesFromString( data ) );
							} catch( IOException e ) {
								throw new ProfileDaoException( "Failed to parse user profile data.", e );
							}
							profiles.add( profile );
						}
						return( profiles );
					}
					
				}
		) );
	}

	@SuppressWarnings("unchecked")
	public Map<Long,UserProfile> findAllMapped() {
		return( (Map<Long,UserProfile>)getJdbcTemplate().query(
				"SELECT id, profile FROM user_profiles WHERE id = ?",
				new ResultSetExtractor() {
					public Object extractData(ResultSet result) throws SQLException, DataAccessException {
						HashMap<Long,UserProfile> profiles = new HashMap<Long,UserProfile>();
						while ( result.next() ) {
							Long id = result.getLong( "id" );
							String data = result.getString( "profile" );
							
							UserProfile profile = new UserProfile();
							profile.setId( id );
							try {
								profile.setProperties( UserProfile.getPropertiesFromString( data ) );
							} catch( IOException e ) {
								throw new ProfileDaoException( "Failed to parse user profile data.", e );
							}
							profiles.put( id, profile );
						}
						return( profiles );
					}
					
				}
		) );
	}
	
	public void update(UserProfile userProfile) {
		try {
			getJdbcTemplate().update(
					"UPDATE user_profiles SET profile = ? WHERE id = ?",
					new Object[]{
							UserProfile.getPropertiesAsString( userProfile.getProperties() ),
							userProfile.getId()
					}
			);
		} catch( IOException e ) {
			throw new ProfileDaoException( "Failed to update user profile.", e );
		}
	}

}
