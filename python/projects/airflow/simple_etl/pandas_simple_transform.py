def main(file: str) -> None:
    import pandas as pd
    from pandas.core.frame import DataFrame

    def transform_col0(s: str) -> list:
        """
        В первом столбце оставить только дату: “1 от 17.09.2018” -> 17.09.2018.
        """
        return s.strip().split(' ')[-1]

    def transform_col3(s: str) -> list:
        """
        Поле с наименованием организации разделить на 2 столбца - правовая форма и название:
        ООО "Континент ЭТС" -> "ООО" | "Континент ЭТС".
        """
        s = s.strip().split(' ')
        return [s[0], ' '.join(s[1:])]

    def transform_col3_1(s: str) -> str:
        """
        Поле с наименованием организации разделить на 2 столбца - правовая форма и название:
        "ООО "Континент ЭТС" -> "ООО" | "Континент ЭТС".\n
        Формирует колонку с правовой формой.
        """
        return s.strip().split(' ')[0].strip()

    def transform_col3_2(s: str) -> str:
        """
        Поле с наименованием организации разделить на 2 столбца - правовая форма и название:
        "ООО "Континент ЭТС" -> "ООО" | "Континент ЭТС".\n
        Формирует колонку с названием.
        """
        return ' '.join(s.strip().split(' ')[1:]).strip()

    def transform_col8(s: str) -> float:
        """
        Предпоследнее значение привести к числовому виду “1 014 430,57” -> 1014430,57.
        """
        if s is None:
            s = '0.0'
        elif s is not str:
            s = str(s)
        return float(s.replace(' ', '').replace(',', '.'))

    def transform_col9(s: str) -> int:
        """
        Даты в последнем столбце разнести в два разных столбца: 2018-2020 -> 2018 | 2020.
        """
        return s.strip().split('-')

    def transform_col9_1(s: str) -> int:
        """
        Даты в последнем столбце разнести в два разных столбца: 2018-2020 -> 2018 | 2020.\n
        Первый столбец.
        """
        return int(s.strip().split('-')[0])

    def transform_col9_2(s: str) -> int:
        """
        Даты в последнем столбце разнести в два разных столбца: 2018-2020 -> 2018 | 2020.\n
        Второй столбец.
        """
        return int(s.strip().split('-')[-1])

    df: DataFrame = pd.read_csv(file, sep=';', header=None) \
        .dropna(how='all') \
        .dropna(axis='columns', how='all')

    print(f'File ({file}) was readed, starting transform.')

    col0 = df[0].apply(transform_col0)
    # col1 = df[3].apply(transform_col3, result_type='expand') WHY IT DOESN'T WORKED?!
    col3_1 = df[3].apply(transform_col3_1)
    col3_2 = df[3].apply(transform_col3_2)
    col8 = df[8].apply(transform_col8)
    # col9 = df[9].apply(transform_col9, result_type='expand') AAAAAARGH!!!
    col9_1 = df[9].apply(transform_col9_1)
    col9_2 = df[9].apply(transform_col9_2)

    fin_df = pd.concat(
        [col0, df[2], col3_1, col3_2, df[[4, 5, 6, 7]], col8, col9_1, col9_2],
        axis=1,
        ignore_index=True)

    print(f'Result for {file}:')
    print(fin_df.info())

    output_file = 'output.csv'
    print(f'Transform completed, saving in file ({output_file}).')

    fin_df.to_csv(output_file, index=False, header=False)
    print('Success!')


if __name__ == '__main__':
    main(file='201170_er.csv')
