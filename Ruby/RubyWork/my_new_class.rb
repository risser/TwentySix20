=begin
  foo test
=end
class MyNewClass
  def initialize(name, salary, hire_year)
    @name = name
    @salary = salary
    @hire_year = hire_year  
  end
  
  def to_s
    "Name is #{@name}"
  end
end
