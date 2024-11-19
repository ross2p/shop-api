INSERT INTO Roles (id, name, description, created_at, updated_at)
VALUES ('a01593ee-052b-44dd-99b2-5cf72a017dbe', 'USER', '', NOW(), NOW()),
       ('edf10bdf-a0ce-4f16-91d7-a9d3dd1f6d35', 'ADMIN', '', Now(), NOW());

INSERT INTO Users (id, first_name, last_name, email, password, birth_date, role_id, created_at, updated_at)
VALUES ('76fffd7c-9c3a-4cab-9bea-ab82b0fd6504', 'User', 'User', 'user@user.com','$2a$10$ezKDvzv1ggI.pa4ty.kVlet4kmONVWae36OO/ZO6N3.UTUTnaWdtO', '2005-06-22T00:00:00.000+00:00', 'a01593ee-052b-44dd-99b2-5cf72a017dbe', NOW(), NOW()),
       ('9f3e06da-a890-4980-b4cc-d9e816f84c97', 'Admin', 'Admin', 'admin@admin.com','$2a$10$ezKDvzv1ggI.pa4ty.kVlet4kmONVWae36OO/ZO6N3.UTUTnaWdtO', '2005-06-22T00:00:00.000+00:00', 'edf10bdf-a0ce-4f16-91d7-a9d3dd1f6d35', NOW(), NOW());


INSERT INTO Orders (id, status, user_id, total_amount, created_at, updated_at)
VALUES ('57c322ae-af6d-4790-b04b-4f84087da515','PENDING', '9f3e06da-a890-4980-b4cc-d9e816f84c97', 0, NOW(), NOW()),
       ('42690fe6-99dd-4fe6-9c5a-c7d013a4aaa4','PENDING', '76fffd7c-9c3a-4cab-9bea-ab82b0fd6504', 0, NOW(), NOW());


INSERT INTO Categories (id, name, description, created_at, updated_at)
VALUES ('70536671-390a-4610-a305-b215da6f9530', 'Products', 'Опис категорії продукти', NOW(), NOW()),
       ('b7a05c61-259f-46ff-9770-3a17a90f176d', 'Clothing', 'Опис категорії одяг', NOW(), NOW());



