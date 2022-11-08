from typing import List
from Person import Person
from Phone import Phone

if __name__ == '__main__':
    print('hello')
    p1 = Person(first_name='Dima', last_name='Zhdanov', phones=[Phone('8800', 'samsung')])
    print(p1)
    p1.phones.append(Phone('8801', 'xiaomi'))
    p1.phones.append(Phone('8802', 'samsung'))
    p2 = Person('Diana', 'Golubeva', [Phone('8803', 'samsung')])
    ps: List[Person] = [p1, p2]
    
    def filter_by_person(p: Person):
        return any(map(lambda x: True if x.model == 'xiaomi' else False, p.phones))

    ps_filter = list(filter(filter_by_person, ps))

    print(ps_filter)

    def filter_by_phone(x: Phone):
        return x.model == 'xiaomi'
    
    p1.phones = list(filter(filter_by_phone, p1.phones))

    print(p1)
