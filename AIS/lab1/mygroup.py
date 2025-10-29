groupmates = [
    {
        "name": "Александр",
        "surname": "Иванов",
        "exams": ["Информатика", "ЭЭиС", "Web"],
        "marks": [4, 3, 5]
    },
    {
        "name": "Иван",
        "surname": "Петров",
        "exams": ["История", "АиГ", "КТП"],
        "marks": [4, 4, 4]
    },
    {
        "name": "Кирилл",
        "surname": "Смирнов",
        "exams": ["Философия", "ИС", "КТП"],
        "marks": [5, 5, 5]
    }
]


from typing import List, Dict


def avg_mark(student: Dict) -> float:
    marks = student.get("marks", [])
    return (sum(marks) / len(marks)) if marks else 0.0


def filter_students_by_avg(students: List[Dict], threshold: float) -> List[Dict]:
    eps = 1e-9
    return [s for s in students if avg_mark(s) >= threshold - eps]


def print_students(students: List[Dict]) -> None:
    if not students:
        print("Подходящих студентов нет.")
        return
    students_sorted = sorted(students, key=lambda s: avg_mark(s), reverse=True)
    for s in students_sorted:
        exams = ", ".join(s.get("exams", []))
        print(f'{s["surname"]} {s["name"]} — ср. балл: {avg_mark(s):.2f}; экзамены: {exams}')


def main() -> None:
    raw = input("Введите порог среднего балла (включительно, можно с запятой): ").strip()
    try:
        threshold = float(raw.replace(",", "."))
    except ValueError:
        print("Ошибка ввода: нужно число (например, 4.0).")
        return
    suitable = filter_students_by_avg(groupmates, threshold)
    print_students(suitable)


if __name__ == "__main__":
    main()
