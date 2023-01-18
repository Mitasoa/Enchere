    create or replace view FicheEncheres as( 
    SELECT PROD.id as id,PROD.NOM as PRODUIT,prod.PRIX,CATEG.NOM AS CATEGORIE,PROD.dateencheriser as datefin,
    case 
       when(prod.etat=0) then ('en cours')
       when(prod.etat=20) then ('Fin') 
    end status 
    FROM 
    PRODUIT AS PROD 
    JOIN CATEGORIE AS CATEG
    ON PROD.CATEGORIEID=CATEG.ID);

    db.Photo.insertOne({"id":1,"sary":"data:image/jpeg;base64,UklGRhAlAABXRUJQVlA4IAQlAAAwkgCdASosASwBPm0yk0ekIqIhJ3W8CIANiWNultc7PdxwfUny+/gBzrLKX25b+Z56/YbtFNP3XfLf8vz2n5vSn4pXTl8xnngf8P12/2j8JfgA/rX+x62b0GvOq9ZX+9f9LqAP//mhnp/2x8If1bsmbv/53gV2wv7vvf/bf5X0CMhewouB6CPs3949PuZx9s0Z+SKeY/5fH7+1eoZ0tfSvO1CbJh7aRYV20iwrtpFhXbSLCu2kWFdtBRmxwpWnyIW9sXosLrJhq2nPWXNirsHmRsyhLsSjfMPUMBAf/mL2+3XgJFy0WSEmV7edwQInUDcWP+o2qKKdwc6rhCxYDsUkzsUd8W8Xxnh/HbXwJGkOcCEaI6v7SPR/XOeP4SN2YNIcEUCWFB2iKeqw8DLqdLoQcNQyv51OwrzOMu8zKw7vDGwGguJqUVzLQ1CDQbXrODv5+NbEAwRfa1H1B0FZBFzyNi+JFnc8zzZmyUiuaNxXLC0UXoeOA5rPiEFBCI9sbqx1q3QNvVvk//2VkB0af3RD67cwed/D4G242Ycf1/934D6LLG/3Ut6uzde+pDKuCf6+tQz8zQY6CZvfynsTUMNNTEb/jW9Ep8sEVc06D0LsivFI/UHxWd7ffLWqJNGFM4DU3IDpc09gGYGYzshbGxHjRKerPcUEAK0Y7YJEo0xeYIpweKjwWl2WJdTRK+K3jDQBWAiTX6DwE+/Tre7tWmsdLSKIb672M00+dETdmOIz2TilR+iYNfBI9k52bfA3pEhFGrobpbNNs+/zKR+ZZdGqvUkV1PNrAkI6w0EgEyfBEowkjSVdJp6zUSS8XS3bhUNtfYBa+VSXEerFAwDVCLo/r7Q4jKDmqgJ67riLjyPdU3/MDD0Yo8TDV7/yTspFxgMgJk9Xn3nk7CNBS5+e+aiP7Bm0vmp2CuE5rayBrZBMSu2HBDlTIJZqt5vDL0+FF2mNgtIHnFLxg9LsMsAWU/Mm1KtHzo7Z+AFmyRI5iLG6R2l6C5tl9VxR8+C5Qzu5iDF9c5IcG4oacXrzBSSg+eLtOMBAsLnxCnRBcC6gy4XDzbF+Zl1u8yJV4e1OsCihmEEWsbHGWTb5sP1kci73wxjFna8fECbHjm31BzLeMe2pbq6s91HwsDV1wrAgvJJSu8am+xwq7CKFhv8M2P4eNo5Uo+SQGWhG7W9743OGvC/u7qghxR97P2bJlzDbLJmHs8Y265TzofQ6rht6S+9/LRBwvjVTx5F+hQZ+l3MuZDy6NNEobFaWVb2FvDxuTySYaYhRpfdaWEK3+uze0MPoWjkPHQOt/JzNrgRyNKQ+k++9iCywEoA0t89wvzGL9ioL26wuo3TJdujBwMZ83fB+WWr2+IiKX9DyrZp4PuWOdGjjwvyhdJNUMPREknHP+4KMBjN6vsQ7BFCTMUUEz39OwvNSaM48ygv51gWvyH6e1DLMQc09KWH2yGOjA3GC3YNZYSPyWNysr04RQBS0PWp0J27+z2NHbeWCN8KVa+0btJLzp3N3qoSzzhDX4h7CdUyv6yEGhaN7P1d+baRYhXA0b2fq7820iwrtpFhHAAD+9rIAAAAAAC0Csvr4j+o+9MP3keDYFF4BeGvWrHDwwqgAScVgH7bx28Op/GLdHBTXj/ZX2OBVe1ScMG5hISXElBgOvfH5ChlnfchXZqwyfWV66fNWtMzyN4GWHfRoDvW88L","idproduit":1});

    select 
    prod.prix as prix,
    util.solde as solde,
    abs(solde-prix) as montantBlocked
    from 
    produit as prod
    join utilisateur as util on util.id=prod.utilisateurid where id=2;

    db.HistoriqueUtilisateur.insertOne({"id":1,"nom":"sofa","prix":20000,"utilisateuridvendeur":1,"utilisateuridvendeur":2,"categorieid":2,"dateencheriser":"2022-02-02","duree":"09:02:00"});





