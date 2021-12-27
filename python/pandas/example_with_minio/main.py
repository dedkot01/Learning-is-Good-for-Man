from datetime import datetime

import pandas as pd
from pandas.core.frame import DataFrame


def transform_col0(s: str) -> list:
    return s.strip().split(' ')[-1]


def transform_col3(s: str) -> list:
    s = s.strip().split(' ')
    return [s[0], ' '.join(s[1:])]


def transform_col3_1(s: str) -> str:
    return s.strip().split(' ')[0].strip()


def transform_col3_2(s: str) -> str:
    return ' '.join(s.strip().split(' ')[1:]).strip()


def transform_col8(s: str) -> float:
    return float(s.replace(' ', '').replace(',', '.'))


def transform_col9(s: str) -> int:
    return s.strip().split('-')


def transform_col9_1(s: str) -> int:
    return int(s.strip().split('-')[0])


def transform_col9_2(s: str) -> int:
    return int(s.strip().split('-')[-1])


def main(file: str):
    df = pd.read_csv(file, sep=';', header=None) \
        .dropna(how='all') \
        .dropna(axis='columns', how='all')

    col0 = df[0].apply(transform_col0)
    # col1 = df[3].apply(transform_col3, result_type='expand') WHY IT DOESN'T WORKED?!
    col3_1 = df[3].apply(transform_col3_1)
    col3_2 = df[3].apply(transform_col3_2)
    col8 = df[8].apply(transform_col8)
    # col9 = df[9].apply(transform_col9, result_type='expand') AAAAAARGH!!!
    col9_1 = df[9].apply(transform_col9_1)
    col9_2 = df[9].apply(transform_col9_2)

    fin_df: DataFrame = pd.concat(
        [col0, df[2], col3_1, col3_2, df[[4, 5, 6, 7]], col8, col9_1, col9_2],
        axis=1,
        ignore_index=True)

    print(f'Result for {file}:')
    print(fin_df.info())

    output_file = f"{datetime.now().strftime('%H:%M-%d%m%Y')}.csv"
    fin_df.to_csv(output_file, index=False, header=False)
    print(f'Saved in "{output_file}"')


if __name__ == '__main__':
    main(file='201170.csv')
