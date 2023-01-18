package com.enchere.enchere.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.enchere.enchere.model.HistoriqueUtilisateur;

public interface HistoriqueDAO extends MongoRepository<HistoriqueUtilisateur, String> {

    List<HistoriqueUtilisateur> findByNom(String nom);
    /*
     * {
     * Query query = new Query();
     * query.addCriteria(Criteria.where("nom").is(nom));
     * List<HistoriqueUtilisateur> resultats = mongoTemplate.find(query,
     * VotreClasseDeModèle.class);
     * return resultats;
     * }
     */

     
/*@Repository
public class VotreClasseDeRepository{

@Autowired
private MongoTemplate mongoTemplate;

public List<VotreClasseDeModèle> filtrerParColonne(String nomColonne, String valeurRecherchée) {
    Query query = new Query();
    query.addCriteria(Criteria.where(nomColonne).is(valeurRecherchée));
    return mongoTemplate.find(query, VotreClasseDeModèle.class);
  }
}*/

}
