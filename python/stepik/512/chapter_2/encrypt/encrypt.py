import simplecrypt

with open("encrypted.bin", "rb") as inp, open('passwords.txt', 'r') as passw:
    encrypted = inp.read()
    passwords = passw.readlines()
    for p in passwords:
        try:
            result = simplecrypt.decrypt(p.strip(), encrypted)
            break
        except simplecrypt.DecryptionException:
            print('{} bad password'.format(p.strip()))
    print(result)