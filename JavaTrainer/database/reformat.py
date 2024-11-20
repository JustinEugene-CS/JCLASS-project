import pandas as pd

df = pd.read_excel("revised.xlsx")

csv = df.to_csv("./revised.csv", index=False)
