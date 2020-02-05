CREATE OR REPLACE VIEW dkp AS
SELECT p.id, p.name, p.class, IFNULL(sum(e.value), 0) AS dkp
FROM player AS p
LEFT JOIN `dkp-entry` AS e ON p.id = e.player
GROUP BY id;
