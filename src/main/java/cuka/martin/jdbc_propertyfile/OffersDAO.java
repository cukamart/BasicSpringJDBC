package cuka.martin.jdbc_propertyfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("offersDao")
public class OffersDAO {

	private NamedParameterJdbcTemplate jdbc;

	// vrati vsetky riadky z tabulky, ktore vyhovuju podmienkam dotazu -> metoda
	// query()
	// tento setter prepojuje dataSource bean s teamto "offersDao beanom..."
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Offer> getOffers() {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", "3");
		params.addValue("name", "Mike");

		return jdbc.query("SELECT * FROM offers where name = :name && id = :id",params, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();

				// setter z Classy Offer predstavujuci tabulku Offer...
				// ResultSet vyuziva mena atributov skutocnej tabulky z databazy
				// offer.setterCoCHcemNastavit(rs.getInt("nazovAtributu"))
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));

				return offer;
			}

		});

	}
	
	// vrati pocet vymazanych riadkov z databazy (v tomto pripade by to malo byt vzdy 1 ak vymaze)
	public int delete(int id){
		
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from offers where id=:id", params);
	}
	
	public boolean create(Offer offer){
		// beanProperty sa o vsetko postara len tie :name : text atd... sa musia rovnako volat ako atributy v beane (offer)
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("insert into offers (name, text, email) values (:name, :text, :email)", params) == 1;
		return true;
	}

	// vrati specificky jeden riadok z tabulky cez metodu queryForObject
	public Offer getOffer(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM offers WHERE id = :id", params, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();

				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));

				return offer;
			}

		});

	}
}
