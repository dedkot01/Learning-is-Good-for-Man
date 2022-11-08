from dataclasses import dataclass, field
from typing import List

from Phone import Phone


@dataclass
class Person:
    first_name: str
    last_name: str
    phones: List[Phone] = field(default_factory=list)
