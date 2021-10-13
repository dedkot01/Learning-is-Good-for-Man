with open("dataset_24465_4.txt", "r") as f, open("reverse.txt", "w") as r:
    r.writelines(f.readlines()[::-1])