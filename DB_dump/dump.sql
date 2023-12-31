PGDMP  7    0                {            accounts    16.1    16.1     $           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            %           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            &           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            '           1262    16389    accounts    DATABASE     t   CREATE DATABASE accounts WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE accounts;
                postgres    false            (           0    0    DATABASE accounts    ACL     1   GRANT ALL ON DATABASE accounts TO account_admin;
                   postgres    false    3367            )           0    0    SCHEMA public    ACL     -   GRANT ALL ON SCHEMA public TO account_admin;
                   pg_database_owner    false    5            �            1259    16428 
   operations    TABLE     �  CREATE TABLE public.operations (
    id bigint NOT NULL,
    amount bigint NOT NULL,
    created timestamp(6) without time zone,
    operation_type character varying(255) NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT operations_operation_type_check CHECK (((operation_type)::text = ANY ((ARRAY['WITHDRAWAL'::character varying, 'REFILL'::character varying, 'OUTGOING_TRANSFER'::character varying, 'INCOMING_TRANSFER'::character varying])::text[])))
);
    DROP TABLE public.operations;
       public         heap    account_admin    false            �            1259    16439    operations_seq    SEQUENCE     x   CREATE SEQUENCE public.operations_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.operations_seq;
       public          account_admin    false            �            1259    16434    user_accounts    TABLE     [   CREATE TABLE public.user_accounts (
    id bigint NOT NULL,
    balance bigint NOT NULL
);
 !   DROP TABLE public.user_accounts;
       public         heap    account_admin    false            �            1259    16440    user_accounts_seq    SEQUENCE     {   CREATE SEQUENCE public.user_accounts_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.user_accounts_seq;
       public          account_admin    false                      0    16428 
   operations 
   TABLE DATA           R   COPY public.operations (id, amount, created, operation_type, user_id) FROM stdin;
    public          account_admin    false    215   s                 0    16434    user_accounts 
   TABLE DATA           4   COPY public.user_accounts (id, balance) FROM stdin;
    public          account_admin    false    216   �       *           0    0    operations_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.operations_seq', 201, true);
          public          account_admin    false    217            +           0    0    user_accounts_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.user_accounts_seq', 1, false);
          public          account_admin    false    218            �           2606    16433    operations operations_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.operations
    ADD CONSTRAINT operations_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.operations DROP CONSTRAINT operations_pkey;
       public            account_admin    false    215            �           2606    16438     user_accounts user_accounts_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.user_accounts
    ADD CONSTRAINT user_accounts_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.user_accounts DROP CONSTRAINT user_accounts_pkey;
       public            account_admin    false    216               �   x�}�Kn�0D��)z����;�MR��)�)��ߢʪI#{+�͈�qqf`�Ap��±���,j�����u�S��5�qi�����x�)�s�R����?u�R����N=E[�I�xX�.�e��?�u�?O��ƥ=Q`a�_���~.or�nå��Wv9�94����=�"T�4�u�.�m�o+���&f�K�B=o��Ӵ��-�?�W�lΦπ'Ҭ���P��6W��oi�SzU�br��o�����            x�3�43461��2�4�0630����� .��     