# インチをcmに変換
per_inch = 2.54

user = input("何インチですか？")

inch = float(user)

cm = inch * per_inch

# 文字列で説明を加える
desc = "{0}インチ={1}センチ".format(inch,cm)
print(desc)
