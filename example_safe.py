# example_safe.py
def get_user_info(user_id):
    query = "SELECT * FROM users WHERE id = %s"
    conn.execute(query, (user_id,))
    return conn.fetchall()

def hash_password(password):
    import hashlib
    return hashlib.sha256(password.encode()).hexdigest()

# Call functions
user_data = get_user_info(1)
secure_password = hash_password("mysecurepassword")

