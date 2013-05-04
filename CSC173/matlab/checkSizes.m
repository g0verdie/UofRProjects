function [x] = checkSizes(A,C)
%check to make sure A and C are necessary sizes
x = 1;
%check A is 2 dimensional
if ndims(A) ~= 2
    x = 0;
end

%check dimSize A
if size(A,1) ~= size(A,2)
    x = 0;
end

%check if C is 1 dimension
if ndims(C) ~= 2
    x = 0;
end

%check C size
if size(C,1) ~= size(A,1)
    x = 0;
end
end

