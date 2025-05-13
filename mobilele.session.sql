SELECT m.name, m.category, m.image_url, m.start_year, m.end_year, b.name as brand_name
FROM models m
JOIN brands b ON m.brand_id = b.id
ORDER BY b.name, m.name;
