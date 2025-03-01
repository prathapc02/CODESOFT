import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
products_data = {
    'Product': ['Product A', 'Product B', 'Product C', 'Product D', 'Product E'],
    'Category': ['Electronics Mobile', 'Furniture Sofa', 'Electronics Laptop', 'Sofa Decor', 'Laptop Accessories']
}
products = pd.DataFrame(products_data)

count_vectorizer = CountVectorizer(tokenizer=lambda x: x.split())

category_matrix = count_vectorizer.fit_transform(products['Category'])

category_df = pd.DataFrame(category_matrix.toarray(), index=products['Product'], columns=count_vectorizer.get_feature_names_out())

print("Category Matrix:")
print(category_df)

product_similarity = cosine_similarity(category_matrix)

product_similarity_df = pd.DataFrame(product_similarity, index=products['Product'], columns=products['Product'])

print("\nProduct Similarity Matrix:")
print(product_similarity_df)

def recommend_similar_products(product, product_similarity_df, top_n=3):
    similar_products = product_similarity_df[product].sort_values(ascending=False)[1:top_n+1]
    return similar_products

# Recommend products similar to 'Product A'
similar_products = recommend_similar_products('Product A', product_similarity_df)
print("\nProducts similar to 'Product A':")
print(similar_products)
