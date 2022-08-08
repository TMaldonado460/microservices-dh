DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products
(
    id_product varchar(255) NOT NULL DEFAULT RANDOM_UUID(),
    created_at  timestamp         NULL,
    name      varchar(255) NULL,
    price      float8       NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id_product)
);

-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Panasonic', 800, NOW());
-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Sony', 700, NOW());
-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Apple', 1000, NOW());
-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Sony Notebook', 1000, NOW());
-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Hewlett Packard', 500, NOW());
-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Bianchi', 600, NOW());
-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Nike', 100, NOW());
-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Adidas', 200, NOW());
-- INSERT INTO public.products (ID_PRODUCT, NAME, PRICE, CREATED_AT) VALUES(RANDOM_UUID(),'Reebok', 300, NOW());
