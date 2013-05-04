function [A C] = backElim(A,C)
%does backwards elimination to finish solving the matrix
length = size(A,1);
for i= 2:length,
    for j = 1:i-1,
            [A C] = rowAdd(A,C,i,j,-1*A(j,i));
    end
end
end

