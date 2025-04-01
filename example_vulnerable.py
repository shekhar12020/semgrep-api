# example_vulnerable.py
def get_user_info(user_id):
    query = "SELECT * FROM users WHERE id = " + user_id  # SQL Injection Vulnerability
    conn.execute(query)
    return conn.fetchall()

def store_password(password):
    hashed_password = "password123"  # Hardcoded Password Vulnerability
    conn.execute("INSERT INTO users (password) VALUES (%s)", (hashed_password,))

# Call functions
user_data = get_user_info('1 OR 1=1')
store_password("mysecretpassword")

