function [A C] = fowardElim(A,C)
%foward elimination of gaussian elimination

length = size(A,1);
%for each column
for i= 1:length,
    %for each row
    [A C] = scalarMult(A,C,i,1/A(i,i));
    for j= (i+1):length,
        [A C] = rowAdd(A,C,i,j,(-1*A(j,i)));
    end
end
end

